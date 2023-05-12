package ru.fllcker.authservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.fllcker.authservice.dto.JwtResponse;
import ru.fllcker.authservice.dto.RefreshRequest;
import ru.fllcker.authservice.dto.SignInDto;
import ru.fllcker.authservice.dto.SignUpDto;
import ru.fllcker.authservice.services.AuthService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("login")
    public ResponseEntity<JwtResponse> login(@RequestBody SignInDto jwtRequest) {
        JwtResponse tokens = authService.login(jwtRequest);
        return ResponseEntity.ok(tokens);
    }

    @PostMapping("signup")
    public ResponseEntity<JwtResponse> signup(@RequestBody @Valid SignUpDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());

        JwtResponse tokens = authService.signup(dto);
        return ResponseEntity.ok(tokens);
    }

    @PostMapping("access")
    public ResponseEntity<JwtResponse> getAccessToken(@RequestBody RefreshRequest request) {
        JwtResponse tokens = authService.getTokensByRefresh(request.getRefreshToken(), false);
        return ResponseEntity.ok(tokens);
    }

    @PostMapping("refresh")
    public ResponseEntity<JwtResponse> getRefreshToken(@RequestBody RefreshRequest request) {
        JwtResponse tokens = authService.getTokensByRefresh(request.getRefreshToken(), true);
        return ResponseEntity.ok(tokens);
    }
}