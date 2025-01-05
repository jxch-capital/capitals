import {instance} from './instance'


const engines = () => instance.get('/stock4j/stock4j/engines');

const stock4j = {
    engines,
}

export default stock4j
