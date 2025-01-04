package io.github.jxch.capitals.stock4j.webapp.controller;

import io.github.jxch.capitals.index3.logic.breadth.BreadthScoreApi;
import io.github.jxch.capitals.index3.model.BreadthRes;
import io.github.jxch.capitals.index3.model.BreathParam;
import io.github.jxch.capitals.stock4j.webapp.api.Index3Api;
import io.github.jxch.capitals.stock4j.webapp.config.CacheKeyGeneratorConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Index3Controller implements Index3Api {
    private final BreadthScoreApi breadthScoreApi;

    @Override
    @Cacheable(cacheNames = "Index3Controller.breath", keyGenerator = CacheKeyGeneratorConfig.INDEX3_BREATH_KEY_GENERATOR)
    public BreadthRes breath(BreathParam breathParam) {
        return breadthScoreApi.index(breathParam);
    }

}
