package io.github.jxch.capitals.index3.logic.breadth.model;

import cn.hutool.core.date.DateUtil;
import io.github.jxch.capitals.index3.model.BreadthCell;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Data
public class BreadthScoreRes {
    private X x;

    public List<List<String>> getRawData() {
        return x.getOpts().getSeries().get(0).getData();
    }

    public List<BreadthCell> getCellList() {
        return getRawData().stream().map(cell -> BreadthCell.builder()
                .date(DateUtil.parseDate(cell.get(0)).toLocalDateTime().toLocalDate())
                .type(cell.get(2))
                .score(Integer.valueOf(cell.get(1).trim()))
                .build()).toList();
    }

    public List<String> getTypes() {
        return getCellList().stream().map(BreadthCell::getType).distinct().sorted().toList();
    }

    public List<BreadthCell> getScoreByType(String type) {
        return getCellList().stream().filter(cell -> Objects.equals(type, cell.getType()))
                .sorted(Comparator.comparing(BreadthCell::getDate).reversed()).toList();
    }

    public List<LocalDate> getDate() {
        return getCellList().stream().map(BreadthCell::getDate).distinct().sorted(Comparator.reverseOrder()).toList();
    }

    public List<BreadthCell> getLast(int len) {
        List<LocalDate> dateList = getDate();
        LocalDate minDate = dateList.subList(0, Math.min(len, dateList.size())).stream().min(Comparator.naturalOrder()).orElseThrow();
        return getCellList().stream().filter(cell -> cell.getDate().toEpochDay() >= minDate.toEpochDay()).toList();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class X {
        private Opts opts;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class Opts {
        private List<Series> series;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class Series {
        private List<List<String>> data;
    }
}
