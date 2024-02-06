package com.travel.busan.service;

import com.travel.busan.dto.BoardFormDto;
import com.travel.busan.entity.Board;
import com.travel.busan.entity.Member;
import com.travel.busan.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public void createBoard(BoardFormDto boardFormDto, Member member){
        Board board = Board.createBoard(boardFormDto);
        board.setMember(member);

        boardRepository.save(board);
    }

}
