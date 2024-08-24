package io.github.jxch.capitals.stock4j.api3.yahoo.csv;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class YahooCsvRes {
    private List<YahooCsvUrlRes> res;
    private String code;
}
