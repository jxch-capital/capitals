import axios from 'axios';

export const instance = axios.create({
    baseURL: 'https://jiangxicheng.online/api/capitals/',
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Origin': 'https://jiangxicheng.online',
        'X-CSRF-TOKEN': ''
    },
    withCredentials: true,
});

