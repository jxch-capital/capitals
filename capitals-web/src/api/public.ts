import {instance} from './instance'

const session = () => instance.get('/session');

export declare type BreathParam = { length: number }
export declare type BreathRes = { type: string[], date: string[], score: number[][], market: number[] }
const breath = (param: BreathParam) => instance.post('/public/stock4j/public/index3/breath', param);

const pub = {
    session,
    breath,
}


export default pub