package com.chys.WebCHYS.ExceptionHandler;

import com.chys.WebCHYS.Model.APIResponse.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<APIResponse<Object>> handleUserNotFoundException(UserNotFoundException ex) {
        APIResponse<Object> response = new APIResponse<>(false, OffsetDateTime.now(), ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        APIResponse<Map<String, String>> response = new APIResponse<>(false, OffsetDateTime.now(), "Validation failed", errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<Object>> handleGenericException(Exception ex) {
        APIResponse<Object> response = new APIResponse<>(false, OffsetDateTime.now(), "Internal Server Error: " + ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler(UserNotFoundException.class)
//    public ResponseEntity<APIResponse<Map<String, String>>> userNotFoundException(UserNotFoundException ex) {
//        Map<String, String> error = new HashMap<>();
//        error.put("error", "User Not Found");
//        error.put("message", ex.getMessage());
//
//        ex.getBindingResult().getFieldErrors().forEach(error ->
//                errors.put(error.getField(), error.getDefaultMessage())
//        );
//        return new ResponseEntity<>(error, OffsetDateTime.now(), HttpStatus.NOT_FOUND, null);
//    }
}
