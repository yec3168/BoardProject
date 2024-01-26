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
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
public class MemberImgService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberImgRepository memberImgRepository;

    @Value("${imgSave.location}")
    private String uploadImage;

    @Autowired
    private FileServie fileServie;


    public void upload(MemberImg memberImg, MultipartFile multipartFile, Member saveMember){
        // 파일 이름, url 재설정.
        UUID uuid = UUID.randomUUID();
        String oriFileNm = multipartFile.getOriginalFilename(); // 실제파일이름
        if(oriFileNm.isEmpty()) {
            memberImg.setMember(saveMember);
            memberImgRepository.save(memberImg);
            return;
        }
        String saveFileNm = uuid.toString()+oriFileNm.substring(oriFileNm.lastIndexOf(".")) ;// .후 제거

        String saveUrl = uploadImage+"/"+saveFileNm;

        //상품 이미지 저장.
        memberImg.updateImg(saveFileNm, saveUrl);
        memberImg.setMember(saveMember);
        memberImgRepository.save(memberImg);

        //파일저장
        try{
            fileServie.uploadFile(saveUrl, saveFileNm, multipartFile);
        }catch (Exception e){
            throw new IllegalStateException("파일을 저장하지 못했습니다.");
        }


    }

}
