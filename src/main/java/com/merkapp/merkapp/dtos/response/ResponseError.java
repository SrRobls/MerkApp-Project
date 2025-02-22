package com.merkapp.merkapp.dtos.response;

public class ResponseError {
    //Esta clase se usa para manejar las respuestas de los errores
    private String message;

    private int code;

    public ResponseError(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public ResponseError(String message) {
        this.message = message;
        this.code = 400;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
