import {instance} from './instance'


const userCookie = () => instance.get('/public/userCookie');
const login = () => instance.get('/login');

const pub = {
    userCookie, login
}


export default pub