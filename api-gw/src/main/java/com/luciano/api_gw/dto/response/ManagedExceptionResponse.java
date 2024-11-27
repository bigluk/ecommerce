package com.luciano.api_gw.dto.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatusCode;


@Data
@Builder
public class ManagedExceptionResponse {

    private HttpStatusCode httpStatusCode;
    private String message;
    private String detail;

}
