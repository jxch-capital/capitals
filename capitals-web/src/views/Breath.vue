<script setup lang="ts">
import api from '@/api';
import {onMounted, ref} from 'vue';
import chroma from 'chroma-js';
import type {BreathRes, BreathParam} from "@/api/public.ts";

const rules = ref({length: {required: true,},});
const breathParam = ref({length: 100} as BreathParam)
const breathRes = ref({} as BreathRes)
const loading = ref(false);

const cellColorScale = chroma.scale(['red', 'green']).domain([0, 100]);
const marketColorScale = chroma.scale(['red', 'green']).domain([0, 1100]);
const getCellColor = (score: number, scale: chroma.Scale) => {
  return scale(score).hex();
};

const query = async () => {
  loading.value = true;
  const res = await api.pub.breath(breathParam.value);
  breathRes.value = res.data as BreathRes;
  loading.value = false;
}

onMounted(() => {
  query();
});

</script>

<template>
  <n-scrollbar x-scrollable>
    <n-card>
      <n-space justify="start">
        <n-button @click="query" :loading="loading">GO</n-button>
        <n-form label-placement="left" :rules="rules">
          <n-form-item label="长度" path="length">
            <n-input-number v-model:value="breathParam.length" clearable/>
          </n-form-item>
        </n-form>
      </n-space>
      <n-space justify="start">
        <n-table size="small" :bordered="false" :bottom-bordered="false" :single-column="true" :single-line="true">
          <tbody v-if="breathRes">
          <tr v-for="(typeScores) in breathRes.score">
            <td v-for="score in typeScores" :style="{ backgroundColor: getCellColor(score, cellColorScale) }">
            </td>
          </tr>
          </tbody>
        </n-table>
      </n-space>
    </n-card>
    <n-table size="small" style="text-align: center"
             :bordered="false" :bottom-bordered="false" :single-column="true" :single-line="true">
      <tbody v-if="breathRes">
      <tr v-for="(typeScores, typeIndex) in breathRes.score">
        <td>{{ breathRes.type[typeIndex] }}</td>
        <td v-for="score in typeScores" :style="{ backgroundColor: getCellColor(score, cellColorScale) }">{{
            score
          }}
        </td>
      </tr>
      <tr>
        <td>MK</td>
        <td v-for="score in breathRes.market" :style="{ backgroundColor: getCellColor(score, marketColorScale) }">
          {{ score }}
        </td>
      </tr>
      <tr>
        <td>DATE</td>
        <td v-for="date in breathRes.date" class="vertical-text">{{ date.replace(/-/g, '') }}</td>
      </tr>
      </tbody>
    </n-table>
  </n-scrollbar>
</template>

<style scoped>

</style>