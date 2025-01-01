import {instance} from './instance'

const userCookie = () => instance.get('/dev/jkl/public/userCookie');

const pub = {
    userCookie
}


export default pub