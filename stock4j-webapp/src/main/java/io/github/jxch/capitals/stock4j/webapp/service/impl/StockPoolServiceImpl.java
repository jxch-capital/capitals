package io.github.jxch.capitals.stock4j.webapp.service.impl;

import io.github.jxch.capitals.cloud.common.MonoUtil;
import io.github.jxch.capitals.stock4j.model.StockPoolDto;
import io.github.jxch.capitals.stock4j.webapp.converter.StockPoolConverter;
import io.github.jxch.capitals.stock4j.webapp.dao.StockPoolDao;
import io.github.jxch.capitals.stock4j.webapp.service.StockPoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StockPoolServiceImpl implements StockPoolService {
    private final StockPoolConverter stockPoolConverter;
    private final StockPoolDao stockPoolDao;

    @Override
    public Mono<List<StockPoolDto>> findByUserid() {
        return MonoUtil.sessionUserid(userid -> stockPoolDao.findByUserid(userid).map(stockPoolConverter::toStockPoolDtoList));
    }

    @Override
    public Mono<StockPoolDto> create(StockPoolDto stockPoolDto) {
        return MonoUtil.sessionUserid(userid -> stockPoolDao.save(stockPoolConverter.toStockPool(stockPoolDto.setUserid(userid)))
                .map(stockPoolConverter::toStockPoolDto));
    }

    @Override
    public Mono<Void> delete(Long id) {
        return stockPoolDao.deleteById(id);
    }

    @Override
    public Mono<StockPoolDto> update(StockPoolDto stockPoolDto) {
        return MonoUtil.sessionUserid(userid -> stockPoolDao.save(stockPoolConverter.toStockPool(stockPoolDto.setUserid(userid)))
                .map(stockPoolConverter::toStockPoolDto));
    }

}
