package com.progressoft.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The ErrorMessage dto class for sending error message.
 * @author mukhtiar.ahmed
 * version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessageDTO implements ResponseDTO {

    private String message;

    private long timestamp;

    private int status;

    private String exception;


}
