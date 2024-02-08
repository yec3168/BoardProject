package com.travel.busan.dto;

import com.travel.busan.entity.Board;
import com.travel.busan.entity.Member;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AnswerDto {

    private Long id;

    @NotBlank(message = "내용은 필수 입력값입니다.")
    private String content;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private Member member;

    private Board board;
}
