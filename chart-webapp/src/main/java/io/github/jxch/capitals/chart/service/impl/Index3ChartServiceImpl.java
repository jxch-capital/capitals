package io.github.jxch.capitals.chart.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import io.github.jxch.capitals.chart.model.BreathParam;
import io.github.jxch.capitals.chart.service.Index3ChartService;
import io.github.jxch.capitals.index3.logic.breadth.BreadthScoreApi;
import io.github.jxch.capitals.index3.logic.breadth.model.BreadthScoreItem;
import io.github.jxch.capitals.index3.logic.breadth.model.BreadthScoreRes;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Index3ChartServiceImpl implements Index3ChartService {
    private final BreadthScoreApi breadthScoreApi;

    @Override
    @SneakyThrows
    public BufferedImage breathUS(BreathParam param) {
        BreadthScoreRes breadth = breadthScoreApi.breadthScore();

        List<String> columns = new ArrayList<>(breadth.getTypes());
        columns.set(0, "DATE");

        Object[][] data = breadth.getDates().stream().sorted(Comparator.reverseOrder()).map(date -> {
            List<String> row = breadth.getRowByData(date).stream().map(BreadthScoreItem::getScore).map(Object::toString).collect(Collectors.toList());
            if (param.isShowDate()) {
                row.set(0, DateUtil.format(date.atStartOfDay(), "yy-MM-dd"));
            }
            return row;
        }).map(list -> list.toArray(new Object[0])).toArray(Object[][]::new);
        data = Arrays.copyOf(data, Math.min(param.getLength(), data.length));

        JTable table = new JTable(new DefaultTableModel(data, columns.toArray()));
        table.setDefaultRenderer(Object.class, new BreathCellRenderer());
        table.setSize(table.getPreferredSize());

        JTableHeader header = table.getTableHeader();
        header.setSize(header.getPreferredSize());

        BufferedImage image = new BufferedImage(table.getPreferredSize().width, table.getPreferredSize().height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        header.paint(g2d);
        g2d.translate(0, header.getHeight());
        table.paint(g2d);
        g2d.dispose();

        BufferedImage rotatedImage = new BufferedImage(image.getHeight(), image.getWidth(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2dRotated = rotatedImage.createGraphics();
        g2dRotated.translate(rotatedImage.getWidth() / 2.0, rotatedImage.getHeight() / 2.0);
        g2dRotated.rotate(Math.toRadians(-90));
        g2dRotated.translate(-image.getWidth() / 2.0, -image.getHeight() / 2.0);
        g2dRotated.drawImage(image, 0, 0, null);

        return rotatedImage;
    }


    private static class BreathCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            setHorizontalAlignment(CENTER);
            component.setBackground(Color.GRAY);
            if (NumberUtil.isNumber(String.valueOf(value))) {
                double cellValue = Double.parseDouble(String.valueOf(value));
                if (cellValue < 0) cellValue = 0;
                if (cellValue > 100) cellValue = 100;
                component.setBackground(getColorForValue(cellValue));

            }

            return component;
        }

        private Color getColorForValue(double value) {
            int red = (int) (255 - value * 2.55);  // 0 -> 255 (Red)
            int green = (int) (value * 2.55);      // 0 -> 255 (Green)

            return new Color(red, green, 0);  // RGB color (Red, Green, Blue)
        }

    }

}
