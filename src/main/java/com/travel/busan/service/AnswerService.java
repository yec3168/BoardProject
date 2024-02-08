package com.travel.busan.service;

import com.travel.busan.dto.BoardFormDto;
import com.travel.busan.entity.Answer;
import com.travel.busan.entity.Board;
import com.travel.busan.entity.Member;
import com.travel.busan.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    public void create(Board board, Member member, String content){
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setUpdateDate(LocalDateTime.now());
        answer.setBoard(board);
        answer.setMember(member);

        answerRepository.save(answer);
    }
}
