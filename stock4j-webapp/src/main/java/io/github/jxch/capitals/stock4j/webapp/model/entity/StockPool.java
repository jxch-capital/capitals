package io.github.jxch.capitals.stock4j.webapp.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Comment;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Comment("股票池")
@Table(name = "stock_pool", indexes = {
        @Index(name = "stock_pool_name", columnList = "name")
})
public class StockPool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Comment("股票池名称")
    private String name;

    @Column(nullable = false, length = 50)
    private String username;

    @Lob
    @Comment("股票代码，逗号区分")
    private String codes;

    @Column(nullable = false)
    @Comment("股票引擎")
    private String engine;
}
