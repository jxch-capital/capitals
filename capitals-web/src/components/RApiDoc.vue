<script setup lang="ts">
import 'rapidoc';
import {useStore} from '@/store';


defineProps<{ apiUrl: string, serverUrl: string }>()
</script>

<template>
  <rapi-doc
      :spec-url="apiUrl"
      :server-url="serverUrl"
      :default-api-server="serverUrl"
      render-style="read"
      theme="dark"
      allow-try="true"
      show-header="true"
      api-key-name="Authorization"
      api-key-location="header"
      :api-key-value="useStore.useAuthStore().getAuthHeader()"
      fetch-credentials="include"
  />
</template>

<style scoped>

</style>