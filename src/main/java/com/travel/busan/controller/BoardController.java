package com.travel.busan.controller;

import com.travel.busan.dto.BoardFormDto;
import com.travel.busan.entity.Board;
import com.travel.busan.entity.Member;
import com.travel.busan.repository.AnswerRepository;
import com.travel.busan.repository.BoardRepository;
import com.travel.busan.repository.MemberRepository;
import com.travel.busan.service.AnswerService;
import com.travel.busan.service.BoardService;
import com.travel.busan.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

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

    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private AnswerService answerService;

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
    public String boardList(Model model, @RequestParam(value = "page", defaultValue = "0")int page){
        Page<Board> paging = boardService.getList(page);
        model.addAttribute("paging", paging);
        return "board/BoardList";
    }

    @GetMapping("/detail/{id}")
    public String detailPage(@PathVariable("id")Long id, Model model)throws Exception{
        try {
            BoardFormDto boardFormDto = boardService.findBoard(id);
            model.addAttribute("boardFormDto", boardFormDto);
        }catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "board/BoardDetail";
    }

    @PostMapping("/answer/create/{id}")
    public String boardAnswer(@PathVariable("id") Long id, Principal principal ,@RequestParam(value="content") String content,Model model)throws Exception{
        try {
            Optional<Board> op =boardRepository.findById(id);
            Member findMember = memberRepository.findByEmail(principal.getName());
            if(op.isPresent()){
                answerService.create(op.get(), findMember,content);
            }
        }catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/board/detail/"+id;
    }

    //@GetMapping("/asd")
   // public String boardhome(Principal principal, Model model){
   //     String email = principal.getName();//로그인한 email이 들어옴
   //     Member findMember = memberRepository.findByEmail(email);
   //     model.addAttribute("member", findMember);
   //     return "layout/BoardLayout";
   // }
 



}
