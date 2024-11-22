package com.luciano.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.luciano.auth.dto.Credential;
import com.luciano.auth.exception.UserNotFoundException;
import com.luciano.auth.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Slf4j
@RestController
@RequestMapping("api/v1/token")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    
    @Operation(summary = "Create a jwt token", 
        description = "Create a jwt token after user authentication", 
        tags = { "Auth" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Jwt created"),
        @ApiResponse(responseCode = "400", description = "Bad Request"), 
        @ApiResponse(responseCode = "401", description = "User not authenticated") 
    })
    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<String> create(@Valid @RequestBody Credential credential) throws Exception {

        log.info("Received new request to create a JWT token");

        Authentication authentication = 
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                                                credential.getUsername(), 
                                                credential.getPassword()));

        if (!authentication.isAuthenticated()) {
            throw new UserNotFoundException("Bad Credential");
        }

        String token = jwtService.createToken((User) authentication.getPrincipal());

        return new ResponseEntity<>(token, HttpStatus.CREATED);

    }


}
