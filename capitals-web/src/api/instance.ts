import axios from 'axios';
import {useStore} from '@/store';

export const instance = axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL,
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
    },
    withCredentials: true,
});

instance.interceptors.request.use(config => {
    if (useStore.useAuthStore().isLoggedIn()) {
        config.headers.Authorization = useStore.useAuthStore().getAuthHeader();
    }
    return config
}, e => Promise.reject(e));

