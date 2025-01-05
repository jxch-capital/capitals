package io.github.jxch.capitals.stock4j.webapp.service.impl;

import io.github.jxch.capitals.cloud.common.MonoUtil;
import io.github.jxch.capitals.stock4j.model.StockPoolDto;
import io.github.jxch.capitals.stock4j.webapp.converter.StockPoolConverter;
import io.github.jxch.capitals.stock4j.webapp.dao.StockPoolDao;
import io.github.jxch.capitals.stock4j.webapp.service.StockPoolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StockPoolServiceImpl implements StockPoolService {
    private final StockPoolConverter stockPoolConverter;
    private final StockPoolDao stockPoolDao;

    @Override
    public Mono<List<StockPoolDto>> findAll() {
        return MonoUtil.sessionUserid(userid -> stockPoolDao.findByUserid(userid).collectList().map(stockPoolConverter::toStockPoolDtoList));
    }

    @Override
    public Mono<StockPoolDto> create(StockPoolDto stockPoolDto) {
        return MonoUtil.sessionUserid(userid -> stockPoolDao.save(stockPoolConverter.toStockPool(stockPoolDto.setUserid(userid)))
                .map(stockPoolConverter::toStockPoolDto));
    }

    @Override
    public Mono<Void> delete(Long id) {
        return MonoUtil.sessionUserid(userid -> stockPoolDao.deleteByIdAndUserid(id, userid));
    }

    @Override
    public Mono<StockPoolDto> update(StockPoolDto stockPoolDto) {
        return MonoUtil.sessionUserid(userid -> stockPoolDao.save(stockPoolConverter.toStockPool(stockPoolDto.setUserid(userid)))
                .map(stockPoolConverter::toStockPoolDto));
    }

}
