package com.luciano.auth.dto;

import org.springframework.http.HttpStatus;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ExceptionDetail {
    
    private HttpStatus httpStatusCode;
    private String message;
    private String detail;

}