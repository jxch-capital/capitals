package io.github.jxch.capitals.index3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "市场呼吸图返回结果")
public class BreadthRes {
    @JsonIgnore
    @Schema(description = "单元得分列表")
    private List<BreadthCell> breadthCells;

    public List<BreadthCell> getType(String type) {
        return breadthCells.stream().filter(cell -> Objects.equals(type, cell.getType()))
                .sorted(Comparator.comparing(BreadthCell::getDate).reversed()).toList();
    }

    @JsonProperty("type")
    @Schema(description = "所有的板块")
    public List<String> getType() {
        return breadthCells.stream().map(BreadthCell::getType).sorted().distinct().toList();
    }

    @JsonProperty("date")
    @Schema(description = "所有的日期")
    public List<LocalDate> getDate() {
        return breadthCells.stream().map(BreadthCell::getDate).sorted(Comparator.reverseOrder()).distinct().toList();
    }

    @JsonProperty("score")
    @Schema(description = "板块得分列表，按type和date属性列表的顺序")
    public List<List<Integer>> getScore() {
        return getType().stream().map(type -> getType(type).stream().map(BreadthCell::getScore).toList()).toList();
    }

}
