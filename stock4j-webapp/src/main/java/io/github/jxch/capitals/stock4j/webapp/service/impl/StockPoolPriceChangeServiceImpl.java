package io.github.jxch.capitals.stock4j.webapp.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import io.github.jxch.capitals.cloud.common.MonoUtil;
import io.github.jxch.capitals.stock4j.api3.lb.StockLBApi;
import io.github.jxch.capitals.stock4j.model.KLine;
import io.github.jxch.capitals.stock4j.model.StockBatchParam;
import io.github.jxch.capitals.stock4j.webapp.converter.StockPoolConverter;
import io.github.jxch.capitals.stock4j.webapp.dao.StockPoolDao;
import io.github.jxch.capitals.stock4j.webapp.model.dto.StockPoolPriceChangeDaily;
import io.github.jxch.capitals.stock4j.webapp.model.dto.StockPoolPriceChangeDailyParam;
import io.github.jxch.capitals.stock4j.webapp.model.dto.StockPoolPriceChangeDailyRes;
import io.github.jxch.capitals.stock4j.webapp.service.StockPoolPriceChangeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StockPoolPriceChangeServiceImpl implements StockPoolPriceChangeService {
    private final StockPoolDao stockPoolDao;
    private final StockPoolConverter stockPoolConverter;
    private final StockLBApi stockLBApi;

    @Override
    public Mono<StockPoolPriceChangeDailyRes> daily(StockPoolPriceChangeDailyParam param) {
        return MonoUtil.sessionUserid(userid -> stockPoolDao.findByUseridAndIdIsIn(userid, param.getStockPoolIds())
                .map(stockPoolConverter::toStockPoolDto)
                .map(stockPoolDto -> stockLBApi.query(StockBatchParam.builder().codes(stockPoolDto.getCodeList()).start(DateUtil.date().offset(DateField.DAY_OF_YEAR, -param.getMaxDailyInterval() * 2 - 10)).build())
                        .getKLines().entrySet().stream().map(entry -> StockPoolPriceChangeDaily.builder()
                                .priceChange(param.getDailyIntervals().stream().collect(Collectors.toMap(interval -> interval, interval -> priceChange(entry.getValue(), interval))))
                                .stockPoolName(stockPoolDto.getName()).stockCode(entry.getKey()).build()))
                .flatMap(list -> Mono.just(StockPoolPriceChangeDailyRes.priceChangeByList(list.toList())))
                .last()
        );
    }

    private Double priceChange(List<KLine> kLines, Integer interval) {
        kLines = kLines.stream().sorted(Comparator.comparing(KLine::getDate).reversed()).toList();
        return (kLines.get(0).getClose() - kLines.get(interval).getClose()) / kLines.get(interval).getClose();
    }

}
