package com.study.spring1team.domain.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PostRequestDTO {

    @NotBlank(message = "제목은 필수 입력 항목입니다.")
    @Size(min = 1, max = 50, message = "제목은 1자 이상 50자 이하로 입력해주세요.")
    private String title;

    @NotBlank(message = "내용은 필수 입력 항목입니다.")
    private String content;

    @NotBlank(message = "작성자는 필수 입력 항목입니다.")
    private String author;
}