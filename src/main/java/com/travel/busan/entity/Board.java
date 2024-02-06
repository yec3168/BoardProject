package com.travel.busan.entity;

import com.travel.busan.dto.BoardForm;
import jakarta.persistence.*;
import jakarta.persistence.criteria.Fetch;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

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

    public Board createBoard(BoardForm boardForm){
        Board board = new Board();
        board.setSubject(boardForm.getSubject());
        board.setContent(boardForm.getContent());
        board.setCreateDate(LocalDateTime.now());
        board.setMember(member);

        return board;
    }
}
