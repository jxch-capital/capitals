<script setup lang="ts">
import api from '@/api'
import {onMounted, ref} from 'vue';
import auth from '../auth'


onMounted( () => {

});

</script>

<template>
  <n-card>
    <n-button @click="auth.login">登录</n-button>
  </n-card>
</template>

<style scoped>

</style>