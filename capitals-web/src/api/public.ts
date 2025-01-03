import {instance} from './instance'

const session = () => instance.get('/public/session');

const pub = {
    session
}


export default pub