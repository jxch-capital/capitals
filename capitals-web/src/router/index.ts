import {h} from 'vue';
import {createRouter, createWebHistory} from 'vue-router';
import {NIcon} from 'naive-ui';
import {BackupTableFilled, HomeOutlined} from "@vicons/material";
import {ApiOutlined} from "@vicons/antd";
import type {Component} from "vue";
import Home from '@/views/Home.vue';
import RApiDoc from "@/components/RApiDoc.vue";

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
        path: '/logging',
        name: 'logging',
        component: () => import('@/views/Logging.vue'),
    },
    {
        path: '/stock-pool',
        name: 'stock-pool',
        whateverLabel: '股票池',
        whateverKey: 'stock-pool',
        icon: renderIcon(BackupTableFilled),
        component: Home,
    },
    {
        path: '/api',
        name: 'api',
        whateverLabel: 'API文档',
        whateverKey: 'api',
        icon: renderIcon(ApiOutlined),
        component: RApiDoc,
        props: () => ({
            apiUrl: "http://localhost:8088/public/doc/stock4j/v3/api-docs",
        }),
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

