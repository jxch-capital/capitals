package io.github.jxch.capitals.stock4j.webapp.converter;

import io.github.jxch.capitals.stock4j.model.StockNameAliasDto;
import io.github.jxch.capitals.stock4j.webapp.model.entity.StockNameAlias;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StockNameAliasConverter {

    StockNameAliasDto toStockNameAliasDto(StockNameAlias po);

    StockNameAlias toStockNameAlias(StockNameAliasDto dto);

    default List<StockNameAliasDto> toStockNameAliasDto(List<StockNameAlias> stockNameAliasList) {
        return stockNameAliasList.stream().map(this::toStockNameAliasDto).toList();
    }

    default List<StockNameAlias> toStockNameAlias(List<StockNameAliasDto> stockNameAliasDtoList) {
        return stockNameAliasDtoList.stream().map(this::toStockNameAlias).toList();
    }

}
