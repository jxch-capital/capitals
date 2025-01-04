import {h} from 'vue';
import {createRouter, createWebHistory, type RouteRecordRaw} from 'vue-router';
import {NIcon} from 'naive-ui';
import type {MenuOption} from 'naive-ui'
import {BackupTableFilled, HomeOutlined} from "@vicons/material";
import {ApiOutlined} from "@vicons/antd";
import {DocumentsOutline} from "@vicons/ionicons5";
import {HeatMap03} from "@vicons/carbon";
import type {Component} from "vue";

function renderIcon(icon: Component) {
    return () => h(NIcon, null, {default: () => h(icon)})
}

export declare type RouteMenu = RouteRecordRaw | MenuOption;

const routes: RouteMenu[] = [
    {
        path: '/',
        name: '',
        component: import('@/views/Home.vue'),
    },
    {
        path: '/home',
        name: 'home',
        whateverLabel: '首页',
        whateverKey: 'home',
        icon: renderIcon(HomeOutlined),
        component: () => import('@/views/Home.vue'),
    },
    {
        path: '/logging',
        name: 'logging',
        component: () => import('@/views/Logging.vue'),
    },
    {
        path: '/breath',
        name: 'breath',
        whateverLabel: '市场呼吸图（热力图）',
        whateverKey: 'breath',
        icon: renderIcon(HeatMap03),
        component: () => import('@/views/Breath.vue'),
    },
    {
        path: '/stock-pool',
        name: 'stock-pool',
        whateverLabel: '股票池',
        whateverKey: 'stock-pool',
        icon: renderIcon(BackupTableFilled),
        component: () => import('@/views/StockPool.vue'),
    },
    {
        whateverLabel: 'API文档',
        whateverKey: 'api',
        icon: renderIcon(DocumentsOutline),
        whateverChildren: [
            {
                path: '/api-stock4j',
                whateverLabel: 'STOCK4J',
                whateverKey: 'api-stock4j',
                icon: renderIcon(ApiOutlined),
            }
        ]
    },
    {
        path: '/api-stock4j',
        name: 'api-stock4j',
        component: () => import('@/components/RApiDoc.vue'),
        props: () => ({
            apiUrl: import.meta.env.VITE_API_STOCK4J_URL,
        }),
    }
];

const vueRouterRoutes: readonly RouteRecordRaw[] = routes.filter(
    (route): route is RouteRecordRaw => 'path' in route && 'component' in route
);

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: vueRouterRoutes
});
router.afterEach((to) => {
    document.title = (to.meta as { title?: string }).title || 'Capitals';
});

export const menu: MenuOption[] = routes.filter(item => item.hasOwnProperty("whateverLabel")) as MenuOption[];
export default router;
