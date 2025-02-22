package com.merkapp.merkapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class MerkAppApiException extends RuntimeException {

    //Esta clase permite lanzar excepciones personalizadas con un mensaje y un código de estado HTTP específico.
    private HttpStatusCode code;

    public MerkAppApiException(String message) {
        super(message);
        this.code = HttpStatus.BAD_REQUEST;
    }

    public MerkAppApiException(String message, Throwable cause) {
        super(message, cause);
        this.code = HttpStatus.BAD_REQUEST;
    }

    public MerkAppApiException(String message, Throwable cause, HttpStatusCode code) {
        super(message, cause);
        this.code = code;
    }

    //Usamos este metodo para lanzar la excepción
    public MerkAppApiException(HttpStatusCode code, String message) {
        super(message);
        this.code = code;
    }

    //El codigo de la excepción (400, 200, 500, etc.)
    public HttpStatusCode getCode() {
        return code;
    }
}
