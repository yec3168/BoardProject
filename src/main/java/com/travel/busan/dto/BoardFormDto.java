package com.travel.busan.dto;

import com.travel.busan.entity.Answer;
import com.travel.busan.entity.Board;
import com.travel.busan.entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BoardFormDto {

    private Long id;

    @NotBlank(message = "제목은 필수 입력값입니다.")
    private String subject;

    @NotEmpty(message = "내용은 필수 입력값입니다.")
    private String content;

    private Member writer; // 작성자

    private List<Answer> answerList = new ArrayList<>();

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    public static BoardFormDto toDto(Board board){
        BoardFormDto boardFormDto = new BoardFormDto();
        boardFormDto.setId(board.getId());
        boardFormDto.setSubject(board.getSubject());
        boardFormDto.setContent(board.getContent());
        boardFormDto.setWriter(board.getWriter());
        boardFormDto.setCreateDate(board.getCreateDate());
        boardFormDto.setUpdateDate(board.getUpdateDate());
        boardFormDto.setAnswerList(board.getAnswer());


        return boardFormDto;
    }
}
