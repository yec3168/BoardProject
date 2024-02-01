package com.travel.busan.dto;

import com.travel.busan.entity.MemberImg;
import lombok.Data;

@Data
public class MemberImgDto {

    private String fileName;

    private String url;

    public static MemberImgDto toDto(MemberImg memberImg){
        MemberImgDto memberImgDto = new MemberImgDto();
        memberImgDto.setFileName(memberImgDto.getFileName());
        memberImgDto.setUrl(memberImgDto.getUrl());

        return memberImgDto;
    }
}
