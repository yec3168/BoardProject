package com.travel.busan.dto;

import com.travel.busan.entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardFormDto {

    private Long id;

    @NotBlank(message = "제목은 필수 입력값입니다.")
    private String subject;

    @NotEmpty(message = "내용은 필수 입력값입니다.")
    private String content;

    private Member writer; // 작성자

    private LocalDateTime createDate;

    private LocalDateTime updateDate;
}
