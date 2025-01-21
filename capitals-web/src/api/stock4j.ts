import {instance} from './instance'


const engines = () => instance.get('/stock4j/stock4j/engines');

export declare type StockPoolPriceChangeDailyParam = {
    stockPoolIds: number[],
    dailyIntervals: number[],
}
const stockPoolPriceChangeDaily = (param: StockPoolPriceChangeDailyParam) => instance.post('/stock4j/stockPool/priceChange/daily', param);

const stock4j = {
    engines,
    stockPoolPriceChangeDaily
}

export default stock4j
