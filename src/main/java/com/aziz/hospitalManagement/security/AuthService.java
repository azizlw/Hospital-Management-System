package com.aziz.hospitalManagement.security;

import com.aziz.hospitalManagement.dto.LoginSignupRequestDto;
import com.aziz.hospitalManagement.dto.LoginResponseDto;
import com.aziz.hospitalManagement.dto.SignupResponseDto;
import com.aziz.hospitalManagement.entity.User;
import com.aziz.hospitalManagement.entity.type.AuthProviderType;
import com.aziz.hospitalManagement.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDto login(LoginSignupRequestDto loginSignupRequestDto) {

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginSignupRequestDto.getUsername(), loginSignupRequestDto.getPassword())
        );

        User user = (User) authentication.getPrincipal();

        String token = authUtil.generateAccessToken(user);
        return new LoginResponseDto(token, user.getId());
    }

    public User signUpInternal(LoginSignupRequestDto signupRequestDto, AuthProviderType authProviderType, String providerId) {
        User user = userRepository.findByUsername(signupRequestDto.getUsername()).orElse(null);

        if(user != null) throw new IllegalArgumentException("User already exists");

        user = User.builder()
                .username(signupRequestDto.getUsername())
                .providerId(providerId)
                .providerType(authProviderType)
                .build();

        if(authProviderType == AuthProviderType.EMAIL) {
            user.setPassword(passwordEncoder.encode(signupRequestDto.getPassword()));
        }

        return userRepository.save(user);
    }

    // login controller
    public SignupResponseDto signup(LoginSignupRequestDto signupRequestDto) {
        User user = signUpInternal(signupRequestDto, AuthProviderType.EMAIL, null);
        return new SignupResponseDto(user.getId(), user.getUsername());

    }

    @Transactional
    public ResponseEntity<LoginResponseDto> handleOAuth2LoginRequest(OAuth2User oAuth2User, String registrationId) {

        AuthProviderType providerType = authUtil.getProviderTypeFromRegistrationId(registrationId);
        String providerId = authUtil.determineProviderIdFromOAuth2User(oAuth2User, registrationId);

        User user = userRepository.findByProviderIdAndProviderType(providerId, providerType).orElse(null);

        String email = oAuth2User.getAttribute("email");
        User emailUser = userRepository.findByUsername(email).orElse(null);

        if(user == null && emailUser == null) {
            // signup flow
            String username = authUtil.determineUsernameFromOAuth2User(oAuth2User, registrationId, providerId);
            user = signUpInternal(new LoginSignupRequestDto(username, null), providerType, providerId);
        } else if (user != null) {
            if(email != null && !email.isBlank() && !email.equals(user.getUsername())) {
                user.setUsername(email);
                userRepository.save(user);
            }
        } else {
            throw new BadCredentialsException("This email is already registered with provider " + emailUser.getProviderType());
        }

        LoginResponseDto loginResponseDto = new LoginResponseDto(authUtil.generateAccessToken(user), user.getId());

        return ResponseEntity.ok(loginResponseDto);
    }
}
