package com.progressoft.warehouse.exception;
/**
 *
 * @author  mukhtiar.ahmed
 * version 1.0
 */
public class ConfigurationException extends WarehouseException {
    public ConfigurationException(String message) {
        super(message);
    }

    public ConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
