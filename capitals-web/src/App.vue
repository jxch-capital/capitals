<script setup lang="ts">
import {provide} from "vue";
import Main from './components/Main.vue'
import {darkTheme} from 'naive-ui'
import {THEME_KEY} from 'vue-echarts';

provide(THEME_KEY, 'dark');
</script>

<template>
  <n-config-provider :theme="darkTheme">
    <n-notification-provider>
      <Main/>
    </n-notification-provider>
  </n-config-provider>
</template>

<style scoped>

</style>
