package com.luciano.api_gw.exception.handler;

import com.luciano.api_gw.dto.response.ManagedExceptionResponse;
import com.luciano.api_gw.exception.JwtTokenMalformedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class GtwExceptionHandler {


    @ExceptionHandler(JwtTokenMalformedException.class)
    public ResponseEntity<ManagedExceptionResponse> tokenValidationException(JwtTokenMalformedException e) {

        log.error("Error during jwt token validation with message: {}", e.getMessage());

        ManagedExceptionResponse response = ManagedExceptionResponse.builder()
                                            .httpStatusCode(HttpStatus.UNAUTHORIZED)
                                            .message("UNAUTHORIZED")
                                            .detail("You have no permission to access the content")
                                            .build();

        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ManagedExceptionResponse> genericException (Exception e) {

        log.error("Error during jwt token validation with message: {}", e.getMessage());

        ManagedExceptionResponse response = ManagedExceptionResponse.builder()
                .httpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .message("We had a problem")
                .detail("Sorry, we encountered an internal problem")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);

    }


}
