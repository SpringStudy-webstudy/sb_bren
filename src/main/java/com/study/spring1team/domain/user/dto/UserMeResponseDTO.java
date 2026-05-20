package com.study.spring1team.domain.user.dto;

public record UserMeResponseDTO(
        Long id,
        String email,
        String name
) {
}