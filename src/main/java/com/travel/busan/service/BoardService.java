package com.travel.busan.service;

import com.travel.busan.dto.BoardFormDto;
import com.travel.busan.entity.Board;
import com.travel.busan.entity.Member;
import com.travel.busan.repository.BoardRepository;
import com.travel.busan.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberRepository memberRepository;

    public BoardFormDto findBoard(Long id) throws Exception{
        Optional<Board> op= boardRepository.findById(id);
        if(op.isPresent()){
            BoardFormDto boardFormDto = BoardFormDto.toDto(op.get());
            return boardFormDto;
        }
        else{
            throw new IllegalStateException("작성한 게시물이 존재하지 않습니다.");
        }
    }

    public void createBoard(BoardFormDto boardFormDto, String email){
        Member findMember = memberRepository.findByEmail(email);
        Board board = Board.createBoard(boardFormDto, findMember);
        boardRepository.save(board);
    }

    public Page<Board> getList(int page){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return boardRepository.findAll(pageable);
    }

}
