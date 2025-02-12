package io.github.jxch.capitals.stock4j.model;

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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "股票别名自定义")
public class StockNameAliasDto {
    @Schema(description = "主键")
    @Null(message = "创建股票别名时，不能传 id", groups = CreateGroup.class)
    @NotNull(message = "根据 id 操作数据库，所以不能为空", groups = {UpdateGroup.class, DeleteGroup.class})
    private Long id;
    @Schema(description = "用户id")
    @Null(message = "不能传 userid", groups = {CreateGroup.class, DeleteGroup.class, SelectGroup.class, UpdateGroup.class})
    private String userid;
    @Schema(description = "股票代码")
    @NotNull(message = "创建股票别名时，股票代码不能为空", groups = CreateGroup.class)
    private String stockCode;
    @NotNull(message = "创建股票别名时，股票代码对应的别名不能为空", groups = CreateGroup.class)
    @Schema(description = "股票别名")
    private String stockAlias;
    @Schema(description = "备注")
    private String remark;
}
