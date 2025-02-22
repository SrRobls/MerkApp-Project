package com.merkapp.merkapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.merkapp.merkapp.dtos.response.ResponseError;

import io.swagger.v3.oas.annotations.Hidden;

@ControllerAdvice //Este decorador indica que esta clase manejará todas las excepciones en la API
@Hidden // Para ocultar su documentación en swagger
public class ExceptionsHandler {
    //Esta clae maneja los tipos de excepciones que pueden pasar en la app

    @ExceptionHandler(MerkAppApiException.class)
    //Este metodo captura cualquier excepcion de tipo MerkAppApiException, en la cual recibe el mensaje, y el valor del tipo/codigo del error
    //y los restorna en un Response
    public ResponseEntity<ResponseError> handleEntrevistaApiException(MerkAppApiException e) {
        ResponseError error = new ResponseError(e.getMessage(), e.getCode().value());
        return new ResponseEntity<>(error, e.getCode());
    }

    @ExceptionHandler(value = { HttpMessageNotReadableException.class })
    // Maneja errores cuando el cuerpo de la solicitud (JSON) es inválido.
    public ResponseEntity<ResponseError> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(new ResponseError(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> handleGenericException(Exception ex) {
        // Manejar otras excepciones aquí si es necesario
        ResponseError error = new ResponseError(ex.getMessage(), 500);
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
