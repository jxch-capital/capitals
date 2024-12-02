package io.github.jxch.capitals.index3.logic.breadth.model;

import cn.hutool.core.date.DateUtil;
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

    public List<BreadthScoreItem> getItemList() {
        return getRawData().stream().map(item -> BreadthScoreItem.builder()
                .date(DateUtil.parseDate(item.get(0)).toLocalDateTime().toLocalDate())
                .type(item.get(2))
                .score(Integer.valueOf(item.get(1).trim()))
                .build()).toList();
    }

    public List<String> getTypes() {
        return getItemList().stream().map(BreadthScoreItem::getType).distinct().sorted().toList();
    }

    public List<BreadthScoreItem> getScoreByType(String type) {
        return getItemList().stream().filter(item -> Objects.equals(type, item.getType()))
                .sorted(Comparator.comparing(BreadthScoreItem::getDate).reversed()).toList();
    }

    public List<LocalDate> getDate() {
        return getItemList().stream().map(BreadthScoreItem::getDate).distinct().sorted(Comparator.reverseOrder()).toList();
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
