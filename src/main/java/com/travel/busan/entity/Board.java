package com.travel.busan.entity;

import com.travel.busan.dto.BoardFormDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.List;

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

    private int view; // 조회수

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member writer; // 작성자

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Answer> answer;


    public static Board createBoard(BoardFormDto boardFormDto, Member member){
        Board board = new Board();
        board.setSubject(boardFormDto.getSubject());
        board.setContent(boardFormDto.getContent());
        board.setCreateDate(LocalDateTime.now());
        board.setUpdateDate(LocalDateTime.now());
        board.setWriter(member);
        board.setView(boardFormDto.getView());

        return board;
    }

    public void updateBoard(String content){
        this.content = content;
        this.updateDate = LocalDateTime.now();
    }

}
