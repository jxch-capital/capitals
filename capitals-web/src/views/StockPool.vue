<script setup lang="ts">
import api from '@/api';
import {onMounted, ref, h} from "vue";
import {NButton, useMessage} from 'naive-ui'
import type {DataTableColumns} from 'naive-ui'
import type {StockPool} from "@/api/stockPool.ts";
import StockPoolEdit from "@/components/StockPoolEdit.vue";
import StockPoolBubbleEChart from "@/components/StockPoolBubbleEChart.vue";

const stockPoolIds = ref([] as number[])
const dailyIntervals = ref([5, 20, 40])
const createStockPoolParam = ref({
  name: '股票池名称',
  codes: 'SPY,QQQ',
  engine: '',
} as StockPool);
const engineOptions = ref({});
const stockPools = ref([] as StockPool[]);
const stockPoolOptions = ref({});
const stockPoolTableColumns = ref([
  {
    "title": "name",
    "key": "name",
  }, {
    "title": "engine",
    "key": "engine",
  }, {
    "title": "codes",
    "key": "codes",
    "width": 200,
  }, {
    "title": "action",
    "key": "action",
    render(row: StockPool) {
      return h(
          StockPoolEdit,
          {
            row: row,
            onUpdateData: findAllStockPools
          },
      )
    }
  },
] as DataTableColumns<StockPool>);
const stockPoolTableData = stockPools;
const stockPoolTablePagination = ref(false);
const currentStockPool = ref({} as StockPool);

const handleSelectEngine = (key: string) => {
  createStockPoolParam.value.engine = key;
};
const handleSelectStockPool = (key: string) => {
  currentStockPool.value = stockPools.value.find(item => item.name === key) ?? stockPools.value[0];
  stockPoolIds.value = [currentStockPool.value.id as number];
};
const findAllEngines = () => {
  api.stock4j.engines().then(res => {
    const engines = res.data as string[];
    engineOptions.value = engines.map(item => {
      return {"key": item, "label": item}
    });
    createStockPoolParam.value.engine = engines[0];
  });
};
const findLoading = ref(false);
const findAllStockPools = () => {
  findLoading.value = true;
  api.stockPool.findAll().then(res => {
    stockPools.value = res.data as StockPool[];
    stockPoolOptions.value = stockPools.value.map(item => {
      return {"key": item.name, "label": item.name}
    });
    currentStockPool.value = stockPools.value[0];
    stockPoolIds.value = [currentStockPool.value.id as number];
  }).finally(() => {
    findLoading.value = false;
  });
};
const createLoading = ref(false);
const createStockPool = async () => {
  try {
    createLoading.value = true;
    await api.stockPool.create(createStockPoolParam.value);
    findAllStockPools();
  } catch (error) {
    useMessage().error("服务异常");
  } finally {
    createLoading.value = false;
  }
};
onMounted(() => {
  findAllEngines();
  findAllStockPools();
});

</script>

<template>
  <n-space vertical size="small">
    <n-card>
      <n-space justify="space-between">
        <n-space>
          <n-button :loading="findLoading" @click="findAllStockPools">查询</n-button>
          <n-button :loading="createLoading" @click="createStockPool">新增</n-button>
          <n-dropdown trigger="hover" :options="engineOptions" @select="handleSelectEngine">
            <n-button>{{ createStockPoolParam.engine }}</n-button>
          </n-dropdown>
          <n-input placeholder="名称" v-model:value="createStockPoolParam.name"/>
          <n-input placeholder="代码" v-model:value="createStockPoolParam.codes"/>
        </n-space>
        <n-space>
          <n-input-number placeholder="Short" v-model:value="dailyIntervals[0]"/>
          <n-input-number placeholder="Middle" v-model:value="dailyIntervals[1]"/>
          <n-input-number placeholder="Long" v-model:value="dailyIntervals[2]"/>
          <n-dropdown trigger="hover" :options="stockPoolOptions" @select="handleSelectStockPool">
            <n-button>{{ currentStockPool.name }}</n-button>
          </n-dropdown>
        </n-space>
      </n-space>
    </n-card>
    <n-space horizontal>
      <n-card>
        <n-data-table size="small" :ellipsis="true" :columns="stockPoolTableColumns" :data="stockPoolTableData"
                      :pagination="stockPoolTablePagination" :bordered="false"/>
      </n-card>
      <n-card>
        <StockPoolBubbleEChart :stock-pool-ids="stockPoolIds" :daily-intervals="dailyIntervals"/>
      </n-card>
    </n-space>
  </n-space>
</template>

<style scoped>

</style>