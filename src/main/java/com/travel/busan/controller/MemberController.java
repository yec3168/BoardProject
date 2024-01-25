package com.travel.busan.controller;

import com.travel.busan.dto.MemberFormDto;
import com.travel.busan.dto.MemberImgDto;
import com.travel.busan.entity.Member;
import com.travel.busan.entity.MemberImg;
import com.travel.busan.repository.MemberImgRepository;
import com.travel.busan.repository.MemberRepository;
import com.travel.busan.service.MemberImgService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import static java.rmi.server.LogStream.log;

@Controller
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberImgService memberImgService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //회원가입 get
    @GetMapping("/new")
    public String signUpForm(Model model){
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "member/MemberForm";
    }

    // 회원가입 post
    @PostMapping("/new")
    public String signUpForm(@Valid MemberFormDto memberFormDto, @RequestParam("memberImgFile") MultipartFile multipartFile,
                             BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors())
            return "member/MemberForm";

        try{
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberRepository.save(member);
            //이미지 저장을 위한 memberImgService.upload 호출.
            memberImgService.upload(new MemberImg(),multipartFile);

        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "member/MemberForm";
        }

        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm(Model model){
        return "member/MemberLogin";
    }
    @GetMapping("/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디가 존재하지 않습니다.");
        return "member/MemberLogin";
    }


}
