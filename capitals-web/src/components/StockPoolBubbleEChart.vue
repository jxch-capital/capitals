<script setup lang="ts">
import {ref, onMounted} from 'vue';
import type {EChartsOption} from 'echarts';
import api from "@/api";

defineProps<{ stockPoolIds: number[] }>();

const option = ref<EChartsOption>({
  legend: {
    right: '10%',
    top: '3%',
  },
  grid: {
    left: '8%',
    top: '10%'
  },
  xAxis: {
    splitLine: {
      lineStyle: {
        type: 'dashed'
      }
    }
  },
  yAxis: {
    splitLine: {
      lineStyle: {
        type: 'dashed'
      }
    },
    scale: true
  },
  series: []
});

onMounted(() => {
  const param = {stockPoolIds: [12], dailyIntervals: [5, 20, 40]}
  api.stock4j.stockPoolPriceChangeDaily(param).then((res) => {
    option.value.series = Object.keys(res.data.priceChange).map(key => {
      return {
        name: key,
        data: res.data.priceChange[key].map(item => [
          item.priceChange[param.dailyIntervals[0]] ?? null,
          item.priceChange[param.dailyIntervals[1]] ?? null,
          Math.abs(item.priceChange[param.dailyIntervals[2]]) ?? null,
          item.stockCode,
          item.stockPoolName
        ]),
        type: 'scatter',
        symbolSize: function (data) {
          return Math.sqrt(data[2] * 5000);
        },
        label: {
          show: true,
          formatter: function (param) {
            return param.data[3];
          },
          position: 'center'
        },
        itemStyle: {
          color: 'rgba(53,229,15,0.5)',
        }
      };
    });
  });
});


</script>

<template>
  <v-chart class="chart" :option="option" autoresize/>
</template>

<style scoped>
.chart {
  height: 50vh;
  width: 50vw;
}
</style>
