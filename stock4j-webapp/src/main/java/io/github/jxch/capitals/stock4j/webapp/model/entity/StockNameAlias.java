package io.github.jxch.capitals.stock4j.webapp.model.entity;

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
@Table("stock_name_alias")
public class StockNameAlias {
    @Id
    private Long id;

    @Column("userid")
    private String userid;

    @Column("stock_code")
    private String stockCode;

    @Column("stock_alias")
    private String stockAlias;

    @Column("remark")
    private String remark;
}
