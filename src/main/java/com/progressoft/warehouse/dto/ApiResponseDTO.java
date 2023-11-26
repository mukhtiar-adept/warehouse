package com.progressoft.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The ApiResponseDTO dto class for sending response.
 * @author mukhtiar.ahmed
 * version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDTO<T> implements ResponseDTO {

    private String message;

    private String status;

    private T Data;

}
