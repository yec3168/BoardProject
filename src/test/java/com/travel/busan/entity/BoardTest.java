package com.travel.busan.entity;

import com.travel.busan.repository.BoardRepository;
import com.travel.busan.repository.MemberRepository;
import com.travel.busan.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class BoardTest {

    @Autowired
    public BoardService boardService;

    @Autowired
    public BoardRepository boardRepository;

    @Autowired
    public MemberRepository memberRepository;
    @Test
    void testJpa() {
        for (int i = 1; i <= 300; i++) {
            String subject = String.format("테스트 데이터입니다:[%03d]", i);
            String content = "내용무"+i;

            Board board = new Board();
            board.setSubject(subject);
            board.setContent(content);
            board.setCreateDate(LocalDateTime.now());
            board.setUpdateDate(LocalDateTime.now());
            board.setWriter(memberRepository.findByEmail("1@1"));

            boardRepository.save(board);

        }
    }
}
