package com.sparta.delivery.config.global.exception;

import com.sparta.delivery.config.global.exception.custom.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> illegalArgumentException(IllegalArgumentException ex){
        int status = HttpServletResponse.SC_BAD_REQUEST;
        ExceptionResponse response = new ExceptionResponse("Bad Request",ex.getMessage(), status);
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionResponse> nullPointerException(NullPointerException ex){
        int status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        ExceptionResponse response = new ExceptionResponse("INTERNAL_SERVER_ERROR", ex.getMessage(), status);
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ExceptionResponse> forbiddenException(ForbiddenException ex){
        int status = HttpServletResponse.SC_FORBIDDEN;
        ExceptionResponse response = new ExceptionResponse("FORBIDDEN", ex.getMessage(), status);
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> userNotFoundException(UserNotFoundException ex){
        int status = HttpServletResponse.SC_NOT_FOUND;
        ExceptionResponse response = new ExceptionResponse("User_Not_Found", ex.getMessage(), status);
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(DuplicateProductException.class)
    public ResponseEntity<ExceptionResponse> duplicateProductException(DuplicateProductException ex){
        int status = HttpServletResponse.SC_CONFLICT;
        ExceptionResponse response = new ExceptionResponse("CONFLICT", ex.getMessage(), status);
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(PaymentAlreadyCompletedException.class)
    public ResponseEntity<ExceptionResponse> PaymentAlreadyCompletedException(PaymentAlreadyCompletedException ex){
        int status = HttpServletResponse.SC_CONFLICT;
        ExceptionResponse response = new ExceptionResponse("Already Paid", ex.getMessage(), status);
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(ExistCardException.class)
    public ResponseEntity<ExceptionResponse> ExistCardException(ExistCardException ex){
        int status = HttpServletResponse.SC_CONFLICT;
        ExceptionResponse response = new ExceptionResponse("Already Register Card", ex.getMessage(), status);
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> exception(Exception ex){
        int status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        ExceptionResponse response = new ExceptionResponse("그 외 모든 에러", ex.getMessage(), status);
        return ResponseEntity.status(status).body(response);
    }

}
