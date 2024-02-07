package com.travel.busan.service;

import com.travel.busan.dto.BoardFormDto;
import com.travel.busan.entity.Board;
import com.travel.busan.entity.Member;
import com.travel.busan.repository.BoardRepository;
import com.travel.busan.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberRepository memberRepository;

    public void createBoard(BoardFormDto boardFormDto, String email){
        Member findMember = memberRepository.findByEmail(email);
        Board board = Board.createBoard(boardFormDto, findMember);
        boardRepository.save(board);
    }

}
