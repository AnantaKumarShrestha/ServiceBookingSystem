package com.morgan.project1.servicebookingsystem.exception;

import com.morgan.project1.servicebookingsystem.enums.Status;
import com.morgan.project1.servicebookingsystem.payload.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(RuntimeException e){
        String message=e.getMessage();
        ApiResponse apiResponse=new ApiResponse(message, Status.FAIL);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgsNotValidException(MethodArgumentNotValidException e){
        Map<String,String> resp=new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(ex->{
            String fieldName= ((FieldError)ex).getField();
            String message=ex.getDefaultMessage();
            resp.put(fieldName,message);
        });
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse> duplicateEmailFoundExceptionHandler(RuntimeException e){
        String message="Duplicate email";
        ApiResponse apiResponse=new ApiResponse(message,Status.FAIL);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

}
