<script setup lang="ts">
import {darkTheme} from 'naive-ui'
import Main from './components/Main.vue'
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
