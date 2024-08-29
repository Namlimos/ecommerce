package com.ecommerce.Service.Implement;

import com.ecommerce.DTO.AuthenticationRequest;
import com.ecommerce.DTO.AuthenticationResponse;
import com.ecommerce.DTO.RegisterRequest;
import com.ecommerce.Entity.User.Role;
import com.ecommerce.Entity.User.User;
import com.ecommerce.Repository.UserRepository;
import com.ecommerce.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImp implements AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<AuthenticationResponse> register(RegisterRequest request) {
        log.info("Attempting to register user: {}", request.getEmail());

        var user = User.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        // Save the user and log success
        repository.save(user);
        log.info("User saved: {}", request.getEmail());

        var jwtToken = jwtService.generateToken(user);
        log.info("Generated JWT Token: {}", jwtToken);

        return ResponseEntity.ok(AuthenticationResponse.builder()
                .token(jwtToken)
                .build());
    }

    @Override
    public ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest request) {
        try {
            // Authenticate the user with the provided credentials
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            // Find the user in the database
            var user = repository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Generate a JWT token for the authenticated user
            var jwtToken = jwtService.generateToken(user);

            // Return the JWT token in the response
            return ResponseEntity.ok(AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build());

        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();

            // If authentication fails, return a 403 Forbidden response
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

}
