package com.travel.busan.entity;

import com.travel.busan.dto.BoardFormDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String subject;

    private String content;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 작성자

    public static Board createBoard(BoardFormDto boardFormDto){
        Board board = new Board();
        board.setSubject(boardFormDto.getSubject());
        board.setContent(boardFormDto.getContent());
        board.setCreateDate(LocalDateTime.now());

        return board;
    }
}
