package com.travel.busan.controller;

import com.travel.busan.dto.MemberFormDto;
import com.travel.busan.entity.Member;
import com.travel.busan.entity.MemberImg;
import com.travel.busan.repository.MemberImgRepository;
import com.travel.busan.repository.MemberRepository;
import com.travel.busan.service.MemberImgService;
import com.travel.busan.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberImgRepository memberImgRepository;

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
    public String signUpForm(@Valid @ModelAttribute MemberFormDto memberFormDto, BindingResult bindingResult ,
                             @RequestParam("memberImgFile") MultipartFile multipartFile, Model model){

        if(bindingResult.hasErrors())
            return "member/MemberForm";

        try{
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            Member saveMember = memberService.save(member);
                //이미지 저장을 위한 memberImgService.upload 호출.
            memberImgService.upload(new MemberImg(),multipartFile, saveMember);;
        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "member/MemberForm";
        }

        return "redirect:/";
    }

    //login Mapping
    @GetMapping("/login")
    public String loginForm(Model model){
        return "member/MemberLogin";
    }
    @GetMapping("/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 혹은 비밀번호가 틀렸습니다.");
        return "member/MemberLogin";
    }



    //memberInfo mapping
    @GetMapping("/memberInfo")
    public String infoView(@AuthenticationPrincipal MemberFormDto memberFormDto, Model model) throws Exception{
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String email = ((UserDetails) principal).getUsername();

        Member findMember = memberRepository.findByEmail(email);

        MemberFormDto findMemberFormDto = MemberFormDto.toDto(findMember);

        if(findMember != null){
            MemberImg findMemberImg = memberImgRepository.findByMember(findMember);
            model.addAttribute("memberFormDto", findMemberFormDto);
            model.addAttribute("memberImg", findMemberImg);

            return "member/MemberInfo";
        }
        else{
            model.addAttribute("errorMessage", "불러 올 수 없습니다.");
            return "redirect:/";
        }
    }

    @GetMapping("/update/{id}")
    public String memberUpdateForm(@PathVariable("id")Long id, Model model){

        Member findMember = memberService.memberView(id);
        MemberFormDto findMemberFormDto = MemberFormDto.toDto(findMember);
        MemberImg memberImg = memberImgRepository.findByMember(findMember);

        model.addAttribute("memberFormDto", findMemberFormDto);
        model.addAttribute("memberImg", memberImg);
        return "member/MemberUpdateForm";
    }

    @PostMapping("/update/{id}")

    public String memberUpdatePost(@Valid @ModelAttribute MemberFormDto memberFormDto, BindingResult bindingResult,
                                   @RequestParam("memberImgFile") MultipartFile multipartFile ,Model model) throws Exception{
        if(bindingResult.hasErrors()){
            return "member/MemberUpdateForm";
        }
        try{
            System.out.println("파일수정을 시작합니다.");
            memberImgService.updateImg(multipartFile, memberRepository.findByEmail(memberFormDto.getEmail()).getMemberImg());

            memberService.updateMember(memberFormDto); //내용 수정.

        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "member/MemberUpdateForm";
        }

        return "redirect:/";
    }
}
