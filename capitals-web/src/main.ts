import {createApp, provide} from 'vue'
import {createPinia} from 'pinia';
import piniaPluginPersistedState from 'pinia-plugin-persistedstate';
import './style.css'
import App from './App.vue'
import 'vfonts/Lato.css'
import 'vfonts/FiraCode.css'
import router from './router';
import VChart from 'vue-echarts';
import '@/plugin'

const pinia = createPinia()
pinia.use(piniaPluginPersistedState)

createApp(App)
    .component('v-chart', VChart)
    .use(router)
    .use(pinia)
    .mount('#app')
