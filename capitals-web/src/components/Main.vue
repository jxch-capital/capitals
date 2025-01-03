<script setup lang="ts">
import Menu from "./Menu.vue";

</script>

<template>
  <n-space horizontal size="small">
    <n-layout has-sider style="width: 100vw; height: 100vh;">
      <Menu/>
      <router-view/>
    </n-layout>
  </n-space>
</template>

<style scoped>

</style>
