<script setup lang="ts">
import {useRouter} from 'vue-router';
import type {MenuOption} from 'naive-ui'
import {menu} from '@/router'
import {ref} from 'vue'

const naiveMenuOptions: MenuOption[] = menu

const collapsed = ref(true)
const menuOptions = ref(naiveMenuOptions)

const router = useRouter();
const click = (_menu: string, item: any) => {
  router.push(item['path']);
}
</script>

<template>
  <n-layout-sider
      bordered
      collapse-mode="width"
      :collapsed-width="64"
      :width="240"
      :collapsed="collapsed"
      show-trigger
      @collapse="collapsed = true"
      @expand="collapsed = false"
  >
    <n-menu
        :collapsed="collapsed"
        :collapsed-width="64"
        :collapsed-icon-size="22"
        :options="menuOptions"
        key-field="whateverKey"
        label-field="whateverLabel"
        children-field="whateverChildren"
        @update:value="click"
    />
  </n-layout-sider>
</template>

<style scoped>

</style>