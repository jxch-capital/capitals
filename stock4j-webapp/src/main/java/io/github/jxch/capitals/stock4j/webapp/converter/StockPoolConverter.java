package io.github.jxch.capitals.stock4j.webapp.converter;

import io.github.jxch.capitals.stock4j.model.StockPoolDto;
import io.github.jxch.capitals.stock4j.webapp.model.entity.StockPool;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StockPoolConverter {

    StockPool toStockPool(StockPoolDto stockPoolDto);

    StockPoolDto toStockPoolDto(StockPool stockPool);

    default List<StockPoolDto> toStockPoolDtoList(List<StockPool> stockPoolList) {
        return stockPoolList.stream().map(this::toStockPoolDto).toList();
    }

    default List<StockPool> toStockPoolList(List<StockPoolDto> stockPoolDtoList) {
        return stockPoolDtoList.stream().map(this::toStockPool).toList();
    }

}
