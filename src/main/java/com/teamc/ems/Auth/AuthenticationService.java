package com.teamc.ems.Auth;

import com.teamc.ems.config.JwtService;
import com.teamc.ems.user.Role;
import com.teamc.ems.user.User;
import com.teamc.ems.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        userRepo.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .user(user)
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            logger.info("Authenticating user with email: {}", request.getEmail());
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            var user = userRepo.findByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            var jwtToken = jwtService.generateToken(user);
            logger.info("User authenticated successfully: {}", user.getEmail());
            return AuthenticationResponse.builder()
                    .user(user)
                    .token(jwtToken)
                    .build();
        } catch (BadCredentialsException e) {
            logger.error("Invalid email or password for user: {}", request.getEmail(), e);
            throw new RuntimeException("Invalid email or password", e);
        } catch (Exception e) {
            logger.error("Authentication failed for user: {}", request.getEmail(), e);
            throw new RuntimeException("Authentication failed", e);
        }
    }

    public AuthenticationResponse authenticateAdmin(AuthenticationRequest request) {
        try {
            var user = userRepo.findByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            if (!user.getRole().equals(Role.ADMIN)) {
                throw new RuntimeException("Not an admin user");
            }
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .user(user)
                    .token(jwtToken)
                    .build();
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Invalid email or password", e);
        } catch (Exception e) {
            throw new RuntimeException("Authentication failed", e);
        }
    }

    public AuthenticationResponse authenticateEmployee(AuthenticationRequest request) {
        try {
            var user = userRepo.findByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            if (!user.getRole().equals(Role.Employee)) {
                throw new RuntimeException("Not an employee user");
            }
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .user(user)
                    .token(jwtToken)
                    .build();
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Invalid email or password", e);
        } catch (Exception e) {
            throw new RuntimeException("Authentication failed", e);
        }
    }
}
