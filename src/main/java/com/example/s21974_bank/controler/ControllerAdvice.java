package com.example.s21974_bank.controler;

import com.example.s21974_bank.exception.AccountNotFoundException;
import com.example.s21974_bank.exception.ValidationException;
import com.example.s21974_bank.model.request.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException exception){
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());

        if (exception.getErrors() != null && !exception.getErrors().isEmpty()){
            errorResponse = new ErrorResponse("Blad walidacji", exception.getErrors());
        }


        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(AccountNotFoundException exception){
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());

        return ResponseEntity.status(404).body(errorResponse);
    }

}
