<script setup lang="ts">
import api from '../api'

const userCookie = () => {
  const res =  api.pub.userCookie()
  console.log(res)
}

</script>

<template>
  <n-card>
    <n-button @click="userCookie">登录</n-button>
<!--    https://jiangxicheng.online/api/capitals/public/userCookie -->

  </n-card>
</template>

<style scoped>

</style>