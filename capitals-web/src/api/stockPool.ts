import {instance} from './instance'

export declare type StockPool = {
    id?: number,
    userid?: string,
    name: string,
    codes: string,
    engine: string,
}
const create = (param: StockPool) => instance.post('/stock4j/stockPool/create', param);
const update = (param: StockPool) => instance.post('/stock4j/stockPool/update', param);
const del = (id: number) => instance.post('/stock4j/stockPool/delete', null, {params: {id: id}});
const findAll = () => instance.post('/stock4j/stockPool/findAll');

const stockPool = {
    create,
    update,
    del,
    findAll,
}

export default stockPool
