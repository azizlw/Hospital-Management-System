package com.aziz.hospitalManagement.controller;

import com.aziz.hospitalManagement.dto.LoginSignupRequestDto;
import com.aziz.hospitalManagement.dto.LoginResponseDto;
import com.aziz.hospitalManagement.dto.SignupResponseDto;
import com.aziz.hospitalManagement.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginSignupRequestDto loginSignupRequestDto) {
        return ResponseEntity.ok(authService.login(loginSignupRequestDto));
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody LoginSignupRequestDto signupRequestDto) {
        return ResponseEntity.ok(authService.signup(signupRequestDto));
    }

}
