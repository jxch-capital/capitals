<script setup lang="ts">
import {ref, onMounted, watch} from 'vue';
import type {EChartsOption} from 'echarts';
import api from "@/api";
import chroma from 'chroma-js';

const props = defineProps<{ stockPoolIds: number[], dailyIntervals: number[], labelSwitch: boolean }>();

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
  tooltip: {
    trigger: 'item',
    backgroundColor: '#333333',
    textStyle: {
      color: 'snow',
      fontSize: 12
    },
    formatter: function (param: any) {
      const data = param.data;
      const scale = chroma.scale(['#ff0000', '#ffa500', '#00ff00']).domain([-0.4, 0, 0.4]);

      const formatPercentage = (value: number) => {
        return value !== null ? `${(value * 100).toFixed(2)}%` : "N/A";
      };

      return `
    ${param["marker"]} ${data[3]} - ${data[4]}
    <table style="width: 100%; border-collapse: collapse; text-align: left;">
      <tbody>
        <tr>
          <td style="padding: 4px 8px; text-align: left;">Short</td>
          <td style="padding: 4px 8px; text-align: right; background-color: ${scale(data[2]).toString()};">
            ${formatPercentage(data[2])}
          </td>
        </tr>
        <tr>
          <td style="padding: 4px 8px; text-align: left;">Middle</td>
          <td style="padding: 4px 8px; text-align: right; background-color: ${scale(data[0]).toString()};">
            ${formatPercentage(data[0])}
          </td>
        </tr>
        <tr>
          <td style="padding: 4px 8px; text-align: left;">Long</td>
          <td style="padding: 4px 8px; text-align: right; background-color: ${scale(data[1]).toString()};">
            ${formatPercentage(data[1])}
          </td>
        </tr>
      </tbody>
    </table>
  `;
    }

  },
  series: []
});

const update = () => {
  if (props.stockPoolIds.length == 0) {
    return;
  }

  const param = {stockPoolIds: props.stockPoolIds, dailyIntervals: props.dailyIntervals}
  api.stock4j.stockPoolPriceChangeDaily(param).then((res) => {
    option.value.series = Object.keys(res.data.priceChange).map(key => {
      return {
        name: key,
        data: res.data.priceChange[key].map((item: any) => [
          item.priceChange[param.dailyIntervals[1]] ?? null,
          item.priceChange[param.dailyIntervals[2]] ?? null,
          item.priceChange[param.dailyIntervals[0]] ?? null,
          item.stockCode,
          item.stockPoolName
        ]),
        type: 'scatter',
        symbolSize: function (data) {
          return Math.min(Math.max(Math.sqrt(Math.abs(data[2]) * 10000), 10), 80);
        },
        label: {
          show: props.labelSwitch.valueOf(),
          formatter: function (param: any) {
            return param["data"][3];
          },
          position: 'inside',
          textStyle: {
            color: 'snow',
            fontSize: 8,
          }
        },
        itemStyle: {
          color: function (data: any) {
            const scale = chroma.scale(['#ff0000', '#ffa500', '#00ff00']).domain([-0.4, 0, 0.4]);
            return scale(data["value"][2]).toString();
          }
        }
      };
    });
  });
}

onMounted(() => update());

watch(() => props, () => {
      update();
    }, {deep: true}
);

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
