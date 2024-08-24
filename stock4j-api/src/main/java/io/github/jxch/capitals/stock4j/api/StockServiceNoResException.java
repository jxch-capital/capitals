package io.github.jxch.capitals.stock4j.api;

public class StockServiceNoResException extends RuntimeException {
    public StockServiceNoResException(String message) {
        super(message);
    }
}
