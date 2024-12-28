import {createRouter, createWebHistory} from 'vue-router';
import Home from '../view/Home.vue';
import {BackupTableFilled, HomeOutlined} from "@vicons/material";
import type {Component} from "vue";
import {NIcon} from 'naive-ui'
import {h} from 'vue'

function renderIcon(icon: Component) {
    return () => h(NIcon, null, {default: () => h(icon)})
}

const routes = [
    {
        path: '/',
        name: '',
        component: Home,
    },
    {
        path: '/home',
        name: 'home',
        whateverLabel: '首页',
        whateverKey: 'home',
        icon: renderIcon(HomeOutlined),
        component: Home,
    },
    {
        path: '/stock-pool',
        name: 'stock-pool',
        whateverLabel: '股票池',
        whateverKey: 'stock-pool',
        icon: renderIcon(BackupTableFilled),
        component: Home,
    }
];

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes,
});

export const menu = routes.filter(item => item.hasOwnProperty("whateverLabel"));
export default router;

router.afterEach((to) => {
    document.title = (to.meta as { title?: string }).title || 'Capitals';
});

