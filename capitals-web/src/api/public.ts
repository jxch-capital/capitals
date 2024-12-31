import {instance} from './instance'

const redirectUrl = encodeURIComponent('http://localhost:5173/capitals');

const userCookie = () => instance.get('/public/userCookie');
// const login = () => window.location.href = 'https://jiangxicheng.online/api/capitals/oauth2/authorization/keycloak?state=localhost:5173/capitals';
const login = () => window.location.href = `http://localhost:8080/oauth2/authorization/keycloak?redirect-to=${redirectUrl}`;

const pub = {
    userCookie, login
}


export default pub