package com.travel.busan.entity;

import com.travel.busan.dto.MemberFormDto;
import com.travel.busan.status.RoleStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;

    private String name;

    private String password;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private RoleStatus roleStatus;

    private String address;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private MemberImg  memberImg;

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setEmail(memberFormDto.getEmail());
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setName(memberFormDto.getName());
        member.setNickname(memberFormDto.getNickname());
        member.setAddress(memberFormDto.getAddress());
        member.setRoleStatus(RoleStatus.ADMIN);
        return member;
    }


}
