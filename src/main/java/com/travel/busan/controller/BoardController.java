package com.travel.busan.controller;

import com.travel.busan.dto.BoardFormDto;
import com.travel.busan.entity.Board;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
    @GetMapping("/new")
    public String boardNew(Model model){
        model.addAttribute("boardFormDto",new BoardFormDto());
        return "board/BoardForm";
    }

    @PostMapping("/new")
    public String boardNewPost(@Valid @ModelAttribute BoardFormDto boardFormDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "board/BoardForm";
        }

        return "redirect:/board/list";
    }
    @GetMapping("/list")
    public String boardHome(Model model){

        return "board/BoardList";
    }




}
