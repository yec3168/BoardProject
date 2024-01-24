package com.travel.busan.service;

import com.travel.busan.dto.MemberImgDto;
import com.travel.busan.entity.Member;
import com.travel.busan.entity.MemberImg;
import com.travel.busan.repository.MemberImgRepository;
import com.travel.busan.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MemberImgService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberImgRepository memberImgRepository;

    @Value("${imgSave.location}")
    private String uploadImage;


    public void upload(MemberImgDto memberImgDto, String email){
        Member member = memberRepository.findByEmail(email);

        if(member == null){
            throw new UsernameNotFoundException("존재하지 않는 회원입니다.");
        }

        UUID uuid = UUID.randomUUID();


    }

}
