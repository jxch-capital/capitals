package io.github.jxch.capitals.stock4j.webapp.util;

import lombok.SneakyThrows;
import org.springframework.core.io.buffer.DefaultDataBuffer;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.function.Supplier;

public class WebfluxChartUtil {

    @SneakyThrows
    public static DefaultDataBuffer image2Buffer(Supplier<BufferedImage> imageSupplier, String format) {
        var outputStream = new ByteArrayOutputStream();
        ImageIO.write(imageSupplier.get(), format, outputStream);
        return new DefaultDataBufferFactory().wrap(outputStream.toByteArray());
    }

    public static DefaultDataBuffer image2Buffer(Supplier<BufferedImage> imageSupplier) {
        return image2Buffer(imageSupplier, "png");
    }

    public static Mono<ResponseEntity<DefaultDataBuffer>> image2Mono(Supplier<BufferedImage> imageSupplier) {
        return Mono.fromCallable(() -> WebfluxChartUtil.image2Buffer(imageSupplier))
                .subscribeOn(Schedulers.boundedElastic())
                .map(dataBuffer -> ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_PNG)
                        .body(dataBuffer))
                .defaultIfEmpty(ResponseEntity.status(500).build());
    }

}
