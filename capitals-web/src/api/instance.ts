import axios from 'axios';

export const instance = axios.create({
    // baseURL: 'https://jiangxicheng.online/api/capitals/',
    baseURL: 'http://localhost:8080',
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
    },
    withCredentials: true,
});

