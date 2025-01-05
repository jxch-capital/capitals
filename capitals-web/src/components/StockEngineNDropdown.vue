<script setup lang="ts">
import {NButton} from "naive-ui";
import api from "@/api";
import {onMounted, ref} from "vue";

// 定义 Props 和 Emits
defineProps<{ value: string }>();
const emit = defineEmits<{
  (e: "update:value", value: string): void;
}>();

// 下拉选项
const engineOptions = ref<{ key: string; label: string }[]>([]);

// 选中事件
const handleSelectEngine = (key: string) => {
  emit("update:value", key); // 触发事件，通知父组件更新 value
};

// 获取引擎选项数据
const findAllEngines = () => {
  api.stock4j.engines().then((res) => {
    const engines = res.data as string[];
    engineOptions.value = engines.map((item) => ({
      key: item,
      label: item,
    }));
  });
};

// 初始化时加载数据
onMounted(() => {
  findAllEngines();
});
</script>

<template>
  <n-dropdown trigger="hover" :options="engineOptions" @select="handleSelectEngine">
    <n-button>{{ value }}</n-button>
  </n-dropdown>
</template>

<style scoped>

</style>