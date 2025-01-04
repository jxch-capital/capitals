package io.github.jxch.capitals.stock4j.webapp.api;

import io.github.jxch.capitals.cloud.common.api.CapitalsCloudServerNames;
import io.github.jxch.capitals.index3.model.BreadthRes;
import io.github.jxch.capitals.index3.model.BreathParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import reactivefeign.spring.config.ReactiveFeignClient;

@RequestMapping("/public/index3")
@Tag(name = "三方市场指数", description = "三方市场指数（允许公开访问）")
@ReactiveFeignClient(name = CapitalsCloudServerNames.STOCK4J, fallbackFactory = CommonFallbackFactory.class)
public interface Index3Api {

    @PostMapping("breath")
    @Operation(summary = "市场呼吸图", description = "美股板块呼吸图")
    BreadthRes breath(@RequestBody @Valid BreathParam breathParam);

}
