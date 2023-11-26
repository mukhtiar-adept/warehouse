package com.progressoft.warehouse.exception;
/**
 *
 * @author  mukhtiar.ahmed
 * version 1.0
 */
public class EntityAlreadyExistsException extends WarehouseException {
    public EntityAlreadyExistsException(String message) {
        super(message);
    }

    public EntityAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
