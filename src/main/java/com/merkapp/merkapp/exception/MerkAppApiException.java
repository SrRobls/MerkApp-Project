package com.merkapp.merkapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class MerkAppApiException extends RuntimeException {
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

    public MerkAppApiException(HttpStatusCode code, String message) {
        super(message);
        this.code = code;
    }

    public HttpStatusCode getCode() {
        return code;
    }
}
