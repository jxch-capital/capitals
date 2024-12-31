<script setup lang="ts">
import api from '../api'
import {ref} from 'vue'

const isLogin = ref(true)
const cookie = ref("")


api.pub.userCookie().then(res => {
  isLogin.value = res.data !== "";

  if (isLogin.value) {
    cookie.value = res.data
  }
});

</script>

<template>
  <n-card>
    <n-button @click="api.pub.login">登录</n-button>
<!--    https://jiangxicheng.online/api/capitals/public/userCookie -->
    {{ isLogin }}
    {{ cookie}}
  </n-card>
</template>

<style scoped>

</style>