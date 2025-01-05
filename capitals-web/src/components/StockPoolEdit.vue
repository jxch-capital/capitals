<script setup lang="ts">
import {onMounted, ref, watch} from 'vue';
import {NButton, useMessage} from "naive-ui";
import {Edit24Regular, DeleteOff24Regular} from '@vicons/fluent';
import type {StockPool} from "@/api/stockPool.ts";
import StockEngineNDropdown from "@/components/StockEngineNDropdown.vue";
import api from '@/api';

const props = defineProps<{ row: StockPool }>();
const emit = defineEmits<{ (e: 'update-data', newData: StockPool): void; }>();

const local = ref<StockPool>({...props.row});
const isDel = ref(false);
const loading = ref(false);

const confirmDel = () => {
  isDel.value = true;
};
const cancelDel = () => {
  isDel.value = false;
};
const handleNegativeClick = () => {
  local.value = {...props.row};
  cancelDel();
};
const del = async () => {
  try {
    loading.value = true;
    if (local.value.id) {
      await api.stockPool.del(local.value.id);
      emit('update-data', local.value);
    } else {
      useMessage().warning("无法删除，请刷新页面后再试")
    }
  } catch (err) {
    useMessage().error("删除失败")
  } finally {
    loading.value = false;
  }
};
const update = async () => {
  try {
    loading.value = true;
    local.value.userid = undefined;
    await api.stockPool.update(local.value);
    emit('update-data', local.value);
  } catch (err) {
    useMessage().error("更新失败")
  } finally {
    loading.value = false;
  }
};
const updateClick = () => {
  if (isDel.value) {
    del();
  } else {
    update();
  }
};

watch(
    props.row,
    (newValue, _oldValue) => {
      local.value = {...newValue};
    },
    {deep: true}
);

onMounted(() => {
  local.value = {...props.row};
  cancelDel();
});

</script>

<template>
  <n-popconfirm :show-icon="false" @positive-click="updateClick" @negative-click="handleNegativeClick">
    <template #trigger>
      <n-button size="small" :tertiary="true" :strong="true" :loading="loading">Edit</n-button>
    </template>

    <n-form label-placement="left">
      <n-form-item label="引擎">
        <StockEngineNDropdown v-model:value="local.engine"/>
        <n-icon v-if="local.engine != row.engine" size="20" color="orange">
          <Edit24Regular/>
        </n-icon>
      </n-form-item>
      <n-form-item label="名称">
        <n-input placeholder="名称" v-model:value="local.name"/>
        <n-icon v-if="local.name != row.name" size="20" color="orange">
          <Edit24Regular/>
        </n-icon>
      </n-form-item>
      <n-form-item label="代码">
        <n-input placeholder="代码" v-model:value="local.codes" type="textarea"/>
        <n-icon v-if="local.codes != row.codes" size="20" color="orange">
          <Edit24Regular/>
        </n-icon>
      </n-form-item>

      <n-popconfirm :show-icon="true" @positive-click="confirmDel" @negative-click="cancelDel">
        <template #icon>
          <n-icon color="red">
            <DeleteOff24Regular/>
          </n-icon>
        </template>
        <template #trigger>
          <n-button dashed type="error">del</n-button>
        </template>
        确定要删除吗？
      </n-popconfirm>
      <n-divider vertical/>
      <n-switch v-model:value="isDel" :disabled="!isDel"/>

    </n-form>
  </n-popconfirm>
</template>

<style scoped>

</style>