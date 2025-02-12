package io.github.jxch.capitals.stock4j.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.jxch.capitals.stock4j.type.StockEngine;
import io.github.jxch.capitals.valid.CreateGroup;
import io.github.jxch.capitals.valid.DeleteGroup;
import io.github.jxch.capitals.valid.SelectGroup;
import io.github.jxch.capitals.valid.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "股票池对象")
public class StockPoolDto {
    @Schema(description = "股票池对象")
    @Null(message = "创建股票池时，不能传 id", groups = CreateGroup.class)
    @NotNull(message = "根据 id 更新数据库，所以不能为空", groups = UpdateGroup.class)
    @NotNull(message = "根据 id 删除数据库，所以不能为空", groups = DeleteGroup.class)
    private Long id;
    @Schema(description = "用户id")
    @Null(message = "不能传 userid", groups = {CreateGroup.class, DeleteGroup.class, SelectGroup.class, UpdateGroup.class})
    private String userid;
    @NotNull(message = "创建股票池时，股票池名称不能为空", groups = CreateGroup.class)
    @Schema(description = "股票池名称", example = "大盘")
    private String name;
    @NotNull(message = "创建股票池时，股票池代码不能为空", groups = CreateGroup.class)
    @Schema(description = "股票池代码，逗号分隔", example = "QQQ,SPY")
    private String codes;
    @Builder.Default
    @NotNull(message = "创建股票池时，股票引擎不能为空", groups = CreateGroup.class)
    @Schema(description = "股票引擎, 默认 DEFAULT，全部支持", example = "DEFAULT")
    private StockEngine engine = StockEngine.DEFAULT;

    @JsonIgnore
    @Schema(description = "股票代码列表")
    public List<String> getCodeList() {
        return Arrays.stream(codes.split(",")).toList();
    }

}
