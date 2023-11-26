package com.progressoft.warehouse.exception;
/**
 *
 * @author  mukhtiar.ahmed
 * version 1.0
 */
public class WarehouseException extends RuntimeException {
    public WarehouseException(String message) {
        super(message);
    }

    public WarehouseException(String message, Throwable cause) {
        super(message, cause);
    }
}
