import {h} from 'vue';
import {createRouter, createWebHistory} from 'vue-router';
import {NIcon} from 'naive-ui';
import {BackupTableFilled, HomeOutlined} from "@vicons/material";
import {ApiOutlined} from "@vicons/antd";
import {DocumentsOutline} from "@vicons/ionicons5";
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
        component: RApiDoc,
        props: () => ({
            apiUrl: import.meta.env.VITE_API_STOCK4J_URL,
        }),
    }
];

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes
});
router.afterEach((to) => {
    document.title = (to.meta as { title?: string }).title || 'Capitals';
});

export const menu = routes.filter(item => item.hasOwnProperty("whateverLabel"));
export default router;
