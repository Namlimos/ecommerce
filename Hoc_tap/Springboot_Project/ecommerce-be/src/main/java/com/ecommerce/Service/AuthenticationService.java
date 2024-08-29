package com.ecommerce.Service;

import com.ecommerce.DTO.AuthenticationRequest;
import com.ecommerce.DTO.AuthenticationResponse;
import com.ecommerce.DTO.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<AuthenticationResponse> register(RegisterRequest request);

    ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest request);
}
