package io.github.jxch.capitals.stock4j.webapp.model.entity;

import io.github.jxch.capitals.stock4j.type.StockEngine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table("stock_pool")
public class StockPool {

    @Id
    private Long id;

    @Column("userid") // 用户ID
    private String userid;

    @Column("name") // 股票池名称
    private String name;

    @Column("codes") // 股票代码，逗号区分
    private String codes;

    @Column("engine") // 股票引擎
    private StockEngine engine;
}
