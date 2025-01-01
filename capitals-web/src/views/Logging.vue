<script setup lang="ts">
import {onMounted, ref} from 'vue';
import auth from '@/auth';
import {useStore} from '@/store';

const user = ref(useStore.useAuthStore().getCurrentUser())
const isLoggedIn = ref(useStore.useAuthStore().isLoggedIn());

onMounted(async () => {
  user.value = await auth.handleCallback();
  isLoggedIn.value = true;
});

</script>

<template>
  <n-card v-if="!isLoggedIn">
    <n-p>
      <n-gradient-text type="info">Logging in ...</n-gradient-text>
    </n-p>
    <n-spin size="small"/>
  </n-card>
  <n-card v-if="isLoggedIn">
    <n-gradient-text type="success">登录成功</n-gradient-text>
    <n-divider/>
    <n-p>用户名：{{ user.profile.name }}</n-p>
    <n-p>邮箱：{{ user.profile.email }}</n-p>
    <n-p>邮箱是否验证：{{ user.profile.email_verified ? '是' : '否' }}</n-p>
  </n-card>
</template>

<style scoped>

</style>