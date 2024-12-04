package io.github.jxch.capitals.chart.service.impl;

import io.github.jxch.capitals.chart.model.BreathParam;
import io.github.jxch.capitals.chart.model.FearGreedParam;
import io.github.jxch.capitals.chart.service.Index3ChartService;
import io.github.jxch.capitals.index3.dataviz.DatavizGraphDataApi;
import io.github.jxch.capitals.index3.dataviz.model.DatavizGraphDataRes;
import io.github.jxch.capitals.index3.logic.breadth.BreadthScoreApi;
import io.github.jxch.capitals.index3.logic.breadth.model.BreadthScoreItem;
import io.github.jxch.capitals.index3.logic.breadth.model.BreadthScoreRes;
import lombok.RequiredArgsConstructor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;


@Service
@RequiredArgsConstructor
public class Index3ChartServiceImpl implements Index3ChartService, KeyGenerator {
    private final BreadthScoreApi breadthScoreApi;
    private final DatavizGraphDataApi datavizGraphDataApi;

    @Override
    public Object generate(Object target, Method method, Object... params) {
        return LocalDate.now() + params[0].toString();
    }


    @Override
    @Cacheable(cacheNames = "Index3ChartServiceImpl.breath", keyGenerator = "index3ChartServiceImpl")
    public BufferedImage breath(BreathParam param) {
        BreadthScoreRes breath = breadthScoreApi.breadthScore();
        List<String> types = breath.getTypes();
        Integer cellSize = param.getCellSize();

        BufferedImage image = new BufferedImage((param.getLength() + 1) * cellSize, types.size() * cellSize, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        for (int row = 0; row < types.size(); row++) {
            List<Integer> scores = breath.getScoreByType(types.get(row)).stream().map(BreadthScoreItem::getScore).toList().subList(0, param.getLength());

            g.setColor(Color.GRAY);
            g.fillRect(0, row * cellSize, cellSize, cellSize);
            g.setFont(new Font("default", Font.PLAIN, 8));
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(types.get(row)), cellSize / 6, row * cellSize + (int) (cellSize / 1.3));

            g.setFont(new Font("default", Font.PLAIN, 12));
            for (int col = 0; col < scores.size(); col++) {
                int x = col + 1;
                Color color = getColorForValue(scores.get(col));
                g.setColor(color);
                g.fillRect(x * cellSize, row * cellSize, cellSize, cellSize);
                g.setColor(Color.BLACK);
                g.drawString(String.valueOf(scores.get(col)), x * cellSize + cellSize / 6, row * cellSize + (int) (cellSize / 1.3));
            }
        }

        g.dispose();
        return image;
    }

    @Override
    @Cacheable(cacheNames = "Index3ChartServiceImpl.fearGreed", keyGenerator = "index3ChartServiceImpl")
    public BufferedImage fearGreed(FearGreedParam param) {
        var fg = datavizGraphDataApi.graphData().getFearAndGreedHistorical();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        fg.getData().stream().sorted(Comparator.comparing(DatavizGraphDataRes.XYData::getX).reversed())
                .forEach(data -> dataset.addValue(data.getY(), "FearGreed", data.getY()));
        JFreeChart chart = ChartFactory.createLineChart(fg.getRating().toUpperCase() + " - " + String.format("%.1f", fg.getScore()), "Date", "FG", dataset);

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setRenderer(new FearGreedRenderer(dataset));
        plot.getDomainAxis().setVisible(false); // 隐藏 X 轴
        plot.getDomainAxis().setTickLabelsVisible(false); // 隐藏 X 轴标签
        plot.getRangeAxis().setVisible(false); // 隐藏 Y 轴
        plot.getRangeAxis().setTickLabelsVisible(false); // 隐藏 Y 轴标签
        plot.setBackgroundPaint(Color.BLACK);  // 绘图区域背景
        plot.setOutlinePaint(null);            // 去掉外边框
        plot.setDomainGridlinePaint(Color.GRAY); // X 轴网格线颜色
        plot.setRangeGridlinePaint(Color.GRAY); // Y 轴网格线颜色
        chart.getLegend().setVisible(false);
        chart.setBackgroundPaint(Color.BLACK); // 图表背景
        chart.getTitle().setPaint(fg.getScore() > 50 ? Color.GREEN : Color.RED); // 标题字体颜色

        return chart.createBufferedImage(param.getWidth(), param.getHeight());
    }

    private Color getColorForValue(double value) {
        if (value < 0) value = 0;
        if (value > 100) value = 100;

        int red = (int) (255 - value * 2.55);  // 0 -> 255 (Red)
        int green = (int) (value * 2.55);      // 0 -> 255 (Green)

        return new Color(red, green, 0);  // RGB color (Red, Green, Blue)
    }

    @RequiredArgsConstructor
    private class FearGreedRenderer extends LineAndShapeRenderer {
        private final CategoryDataset dataset;

        @Override
        public Paint getItemPaint(int row, int column) {
            setDefaultShapesVisible(false);
            return getColorForValue(dataset.getValue(row, column).doubleValue());
        }
    }

}
