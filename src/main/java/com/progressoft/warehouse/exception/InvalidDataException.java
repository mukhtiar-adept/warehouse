package com.progressoft.warehouse.exception;
/**
 *
 * @author  mukhtiar.ahmed
 * version 1.0
 */
public class InvalidDataException extends WarehouseException {

    public InvalidDataException(String message) {
        super(message);
    }

    public InvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
