package com.luciano.api_gw.exception;

public class JwtTokenMalformedException extends RuntimeException {

    public JwtTokenMalformedException(String message) {
        super(message);
    }

}
