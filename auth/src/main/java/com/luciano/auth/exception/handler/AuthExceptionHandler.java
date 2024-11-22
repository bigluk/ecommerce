package com.luciano.auth.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.luciano.auth.dto.ExceptionDetail;
import com.luciano.auth.exception.UserNotFoundException;


@Slf4j
@ControllerAdvice
public class AuthExceptionHandler {


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionDetail> handleThrowedException (UserNotFoundException ex) {

        log.error(ex.getMessage());

        ExceptionDetail detail = ExceptionDetail.builder()
                                    .httpStatusCode(HttpStatus.NOT_FOUND)
                                    .message("User Not Authenticated")
                                    .detail("User Or Password Not Correct")
                                    .build();
        
        return new ResponseEntity<>(detail, HttpStatus.OK);
    }


    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ExceptionDetail> handleThrowedException (UsernameNotFoundException ex) {

        log.error(ex.getMessage());

        ExceptionDetail detail = ExceptionDetail.builder()
                                    .httpStatusCode(HttpStatus.NOT_FOUND)
                                    .message("User Not Authenticated")
                                    .detail("User Or Password Not Correct")
                                    .build();
        
        return new ResponseEntity<>(detail, HttpStatus.OK);
    }


    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<ExceptionDetail> handleThrowedException (InternalAuthenticationServiceException ex) {

        log.error(ex.getMessage());

        ExceptionDetail detail = ExceptionDetail.builder()
                                    .httpStatusCode(HttpStatus.NOT_FOUND)
                                    .message("User Not Authenticated")
                                    .detail("User Or Password Not Correct")
                                    .build();
        
        return new ResponseEntity<>(detail, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDetail> handleThrowedException (Exception ex) {

        log.error(ex.getMessage());

        ExceptionDetail detail = ExceptionDetail.builder()
                                    .httpStatusCode(HttpStatus.NOT_FOUND)
                                    .message("User Not Authenticated")
                                    .detail("User Or Password Not Correct")
                                    .build();
        
        return new ResponseEntity<>(detail, HttpStatus.OK);
    }

    
}
