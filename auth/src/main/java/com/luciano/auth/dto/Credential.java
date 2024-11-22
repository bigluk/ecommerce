package com.luciano.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class Credential {
    
    @NotBlank
    private String username;
    @NotBlank
    private String password;

}
