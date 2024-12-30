import {instance} from './instance'


const userCookie = () => instance.get('/public/userCookie');

const pub = {
    userCookie,
}


export default pub