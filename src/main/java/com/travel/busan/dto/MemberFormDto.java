package com.travel.busan.dto;

import com.travel.busan.entity.Member;
import com.travel.busan.entity.MemberImg;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MemberFormDto {

    private Long id;

    @NotBlank(message = "이메일은 필수 입력값입니다.")
    private String email;

    @NotBlank(message = "비밀번호 필수 입력값입니다.")
    private String password;

    @NotBlank(message = "이름은 필수 입력값입니다.")
    private String name;

    @NotBlank(message = "닉네임은 필수 입력값입니다.")
    private String nickname;

    @NotBlank(message = "주소는 필수 입력값입니다.")
    private String address;

    private MemberImg memberImg;

    public static MemberFormDto toDto(Member member){
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setId(member.getId());
        memberFormDto.setEmail(member.getEmail());
        memberFormDto.setName(member.getName());
        memberFormDto.setAddress(member.getAddress());
        memberFormDto.setPassword(member.getPassword());
        memberFormDto.setNickname(member.getNickname());
        memberFormDto.setMemberImg(member.getMemberImg());

        return memberFormDto;
    }
}
