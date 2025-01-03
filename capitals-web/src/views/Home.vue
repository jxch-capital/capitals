<script setup lang="ts">
import {onBeforeMount} from 'vue';
import auth from '@/auth'
import api from '@/api'


onBeforeMount( () => {
  api.pub.session();
});

</script>

<template>
  <n-card>
    <n-button @click="auth.login">登录</n-button>
  </n-card>
</template>

<style scoped>

</style>