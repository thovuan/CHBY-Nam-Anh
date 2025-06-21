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

//    @ExceptionHandler(UserNotFoundException.class)
//    public ResponseEntity<APIResponse<Object>> handleUserNotFoundException(UserNotFoundException ex) {
//        APIResponse<Object> response = new APIResponse<>(HttpStatus.NOT_FOUND.value(), OffsetDateTime.now(), ex.getMessage(), null);
//        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        APIResponse<Map<String, String>> response = new APIResponse<>(HttpStatus.BAD_REQUEST.value(), OffsetDateTime.now(), "Validation failed", "VF", errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<APIResponse<Map<String, String>>> userNotFoundException(UserNotFoundException ex) {
        Map<String, String> errorData = new HashMap<>();
        errorData.put("error", "User Not Found");
        errorData.put("message", ex.getMessage());

        APIResponse<Map<String, String>> response = new APIResponse<>();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setDatetime(OffsetDateTime.now());
        response.setMessage(ex.getMessage());
        response.setData(errorData);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<APIResponse<Object>> badCredentialsException(BadCredentialsException ex) {
        APIResponse<Object> response = new APIResponse<>(
                HttpStatus.UNAUTHORIZED.value(),
                OffsetDateTime.now(),
                ex.getMessage(),
                "AUTH_INVALID_CREDENTIALS",
                null
        );

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<APIResponse<Object>> conflictException(ConflictException ex) {
        APIResponse<Object> response = APIResponse.builder()
                .status(HttpStatus.CONFLICT.value())
                .datetime(OffsetDateTime.now())
                .message(ex.getMessage())
                .errorCode(ex.getErrorCode())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<APIResponse<Object>> handleBadRequest(BadRequestException ex) {
        APIResponse<Object> response = APIResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .datetime(OffsetDateTime.now())
                .message(ex.getMessage())
                .errorCode(ex.getErrorCode())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<Object>> handleGenericException(Exception ex) {
        APIResponse<Object> response = new APIResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), OffsetDateTime.now(), "Internal Server Error: " + ex.getMessage(), "ISE", null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
