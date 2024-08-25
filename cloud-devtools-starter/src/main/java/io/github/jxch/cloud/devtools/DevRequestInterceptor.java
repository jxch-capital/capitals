package io.github.jxch.cloud.devtools;

import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.BaggageInScope;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@Profile(AppEnv.DEV)
public class DevRequestInterceptor implements RequestInterceptor {
    @Autowired
    private Tracer tracer;
    @Autowired
    private NamingService namingService;
    @Autowired
    private DevToolsConfig devToolsConfig;

    @Override
    public void apply(RequestTemplate template) {
        BaggageInScope localEnvStatues = tracer.getBaggage(CloudEnv.LOCAL_DEV.name());
        if (Objects.nonNull(localEnvStatues)) {
            log.debug("LOCAL_DEV: {}", localEnvStatues.get());
            String sourceName = template.feignTarget().name();
            BaggageInScope sourceBaggage = tracer.getBaggage(CloudEnvContext.SOURCE_SERVICE_NAME.name());
            if (Objects.nonNull(sourceBaggage) && Objects.equals(sourceName, sourceBaggage.get())) {
                BaggageInScope targetBaggage = tracer.getBaggage(CloudEnvContext.TARGET_SERVICE_NAME.name());
                if (Objects.nonNull(targetBaggage)) {
                    try {
                        List<Instance> instances = namingService.selectInstances(targetBaggage.get(), true);
                        Instance instance = instances.stream().findAny().orElseThrow();
                        template.target(instance.getIp() + ":" + instance.getPort());
                    } catch (Exception e) {
                        log.error("NacosException: [LOCAL_DEV: {}] [SOURCE_SERVICE_NAME: {}] [TARGET_SERVICE_NAME: {}]",
                                localEnvStatues.get(), sourceBaggage.get(), targetBaggage.get(), e);
                        log.warn("取消链路跳转");
                    }
                }
            }
        }

        BaggageInScope countBaggage = tracer.getBaggage(CloudEnvContext.CALLS_COUNT.name());
        if (devToolsConfig.isLocalEnable() && Objects.isNull(countBaggage)) {
            tracer.createBaggage(CloudEnvContext.CALLS_COUNT.name()).set("1");
            tracer.createBaggage(CloudEnv.LOCAL_DEV.name()).set(devToolsConfig.getLocalDev());
            tracer.createBaggage(CloudEnvContext.SOURCE_SERVICE_NAME.name()).set(devToolsConfig.getSourceService());
            tracer.createBaggage(CloudEnvContext.TARGET_SERVICE_NAME.name()).set(devToolsConfig.getTargetService());
        }

        if (Objects.nonNull(countBaggage)) {
            int count = Integer.parseInt(Objects.requireNonNull(countBaggage.get()));
            countBaggage.set(String.valueOf(count + 1));
        }
    }

}
