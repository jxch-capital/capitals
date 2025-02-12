package io.github.jxch.capitals.stock4j.webapp.service.impl;

import io.github.jxch.capitals.cloud.common.MonoUtil;
import io.github.jxch.capitals.stock4j.model.StockNameAliasDto;
import io.github.jxch.capitals.stock4j.webapp.converter.StockNameAliasConverter;
import io.github.jxch.capitals.stock4j.webapp.dao.StockNameAliasDao;
import io.github.jxch.capitals.stock4j.webapp.service.StockNameAliasService;
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
public class StockNameAliasServiceImpl implements StockNameAliasService {
    private final StockNameAliasConverter stockNameAliasConverter;
    private final StockNameAliasDao stockNameAliasDao;

    @Override
    public Mono<List<StockNameAliasDto>> findAll() {
        return MonoUtil.sessionUserid(userid -> stockNameAliasDao.findByUserid(userid).collectList().map(stockNameAliasConverter::toStockNameAliasDto));
    }

    @Override
    public Mono<Void> delete(Long id) {
        return MonoUtil.sessionUserid(userid -> stockNameAliasDao.deleteByIdAndUserid(id, userid));
    }

    @Override
    public Mono<StockNameAliasDto> create(StockNameAliasDto stockNameAliasDto) {
        return MonoUtil.sessionUserid(userid -> stockNameAliasDao.save(stockNameAliasConverter.toStockNameAlias(stockNameAliasDto.setUserid(userid)))
                .map(stockNameAliasConverter::toStockNameAliasDto));
    }

    @Override
    public Mono<StockNameAliasDto> update(StockNameAliasDto stockNameAliasDto) {
        return MonoUtil.sessionUserid(userid -> stockNameAliasDao.save(stockNameAliasConverter.toStockNameAlias(stockNameAliasDto.setUserid(userid)))
                .map(stockNameAliasConverter::toStockNameAliasDto));
    }

}
