package io.github.jxch.capitals.stock4j.webapp.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactivefeign.FallbackFactory;

@Slf4j
@Component
public class CommonFallbackFactory<T> implements FallbackFactory<T> {

    @Override
    public T apply(Throwable throwable) {
        return null;
    }

}
