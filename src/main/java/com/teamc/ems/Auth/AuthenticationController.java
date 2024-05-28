package com.teamc.ems.Auth;

import com.teamc.ems.entity.EMPUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")

public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/Register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
            ) {
                return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate (
            @RequestBody AuthenticationRequest request
            ) {
                return ResponseEntity.ok(service.authenticate(request));
    }
    @PostMapping("/login")
    public ResponseEntity<EMPUser> login(@RequestParam String email, @RequestParam String password) {
        try {
            EMPUser user = service.authenticate(email, password);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
