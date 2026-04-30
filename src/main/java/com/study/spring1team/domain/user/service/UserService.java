package com.study.spring1team.domain.user.service;

import com.study.spring1team.domain.user.dto.UserMeResponseDTO;
import com.study.spring1team.domain.user.entity.User;
import com.study.spring1team.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.spring1team.global.apiPayload.exception.GeneralException;
import com.study.spring1team.global.apiPayload.code.GeneralErrorCode;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserMeResponseDTO getMe(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.UNAUTHORIZED));

        return new UserMeResponseDTO(
                user.getId(),
                user.getEmail(),
                user.getName()
        );
    }
}