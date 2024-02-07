package com.travel.busan.controller;

import com.travel.busan.dto.BoardFormDto;
import com.travel.busan.entity.Board;
import com.travel.busan.entity.Member;
import com.travel.busan.repository.BoardRepository;
import com.travel.busan.repository.MemberRepository;
import com.travel.busan.service.BoardService;
import com.travel.busan.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardService boardService;


    @GetMapping("/new")
    public String boardNew(Model model){
        model.addAttribute("boardFormDto",new BoardFormDto());
        return "board/BoardForm";
    }

    @PostMapping("/new")
    public String boardNewPost(@Valid @ModelAttribute BoardFormDto boardFormDto,
                               BindingResult bindingResult, Principal principal,
                               Model model)throws Exception{
        if(bindingResult.hasErrors()){
            return "board/BoardForm";
        }
        try{
            String email = principal.getName();//로그인한 email이 들어옴
            boardService.createBoard(boardFormDto, email);

        }catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/board/list";
    }
    @GetMapping("/list")
    public String boardHome(Model model){

        return "board/BoardList";
    }

 



}
