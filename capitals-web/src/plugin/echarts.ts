import * as echarts from 'echarts/core';
import { use } from 'echarts/core';
import {CanvasRenderer} from 'echarts/renderers';
import {
    ScatterChart,
    LineChart,
    PieChart,
} from 'echarts/charts';
import {
    TitleComponent,
    TooltipComponent,
    GridComponent,
    DatasetComponent,
    LegendComponent,
} from 'echarts/components';

use([
    CanvasRenderer,
    ScatterChart,
    LineChart,
    PieChart,
    GridComponent,
    TooltipComponent,
    TitleComponent,
    DatasetComponent,
    LegendComponent,
]);

export default echarts;
