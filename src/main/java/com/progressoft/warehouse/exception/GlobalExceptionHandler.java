package com.progressoft.warehouse.exception;

import com.progressoft.warehouse.util.WarehouseHealper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({InvalidDataException.class})
    public ResponseEntity<Object> handleStudentNotFoundException(InvalidDataException exception) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(WarehouseHealper.buildErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage()));
    }

    @ExceptionHandler({EntityAlreadyExistsException.class})
    public ResponseEntity<Object> handleStudentAlreadyExistsException(EntityAlreadyExistsException exception) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(WarehouseHealper.buildErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage()));
    }


    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleValidationExceptions(
        MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(WarehouseHealper.buildErrorResponse(HttpStatus.BAD_REQUEST.value(),  WarehouseHealper.getJsonResponse(errors)));
    }

    @ExceptionHandler({NoResourceFoundException.class})
    public ResponseEntity<Object> handleRuntimeException(NoResourceFoundException exception) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(WarehouseHealper.buildErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage()));
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception) {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(WarehouseHealper.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage()));
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleRuntimeException(Exception exception) {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(WarehouseHealper.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage()));
    }
}
