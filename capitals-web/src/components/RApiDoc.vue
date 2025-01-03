<script setup lang="ts">
import 'rapidoc';

defineProps<{ apiUrl: string }>()
</script>

<template>
  <rapi-doc
      :spec-url="apiUrl"
      render-style="read"
      theme="dark"
      allow-try="true"
      show-header="true"
  />
</template>

<style scoped>

</style>