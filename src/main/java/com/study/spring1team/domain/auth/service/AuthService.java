package com.study.spring1team.domain.auth.service;

import com.study.spring1team.domain.auth.dto.LoginRequestDTO;
import com.study.spring1team.domain.auth.dto.LoginResponseDTO;
import com.study.spring1team.domain.auth.dto.SignupRequestDTO;
import com.study.spring1team.domain.user.entity.User;
import com.study.spring1team.domain.user.repository.UserRepository;
import com.study.spring1team.global.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.study.spring1team.global.apiPayload.exception.GeneralException;
import com.study.spring1team.global.apiPayload.code.GeneralErrorCode;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public void signup(SignupRequestDTO request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new GeneralException(GeneralErrorCode.BAD_REQUEST);
        }

        String encodedPassword = passwordEncoder.encode(request.password());

        User user = User.create(
                request.email(),
                encodedPassword,
                request.name()
        );

        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public LoginResponseDTO login(LoginRequestDTO request) {

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.UNAUTHORIZED));

        boolean passwordMatches = passwordEncoder.matches(
                request.password(),
                user.getPassword()
        );

        if (!passwordMatches) {
            throw new GeneralException(GeneralErrorCode.UNAUTHORIZED);
        }

        String accessToken = jwtProvider.createToken(user.getId());

        return new LoginResponseDTO(accessToken);
    }
}