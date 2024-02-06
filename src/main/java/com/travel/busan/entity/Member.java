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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    private LocalDateTime createDate;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private MemberImg  memberImg;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Board> boardList = new ArrayList<>();

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setEmail(memberFormDto.getEmail());
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setName(memberFormDto.getName());
        member.setNickname(memberFormDto.getNickname());
        member.setAddress(memberFormDto.getAddress());
        member.setRoleStatus(RoleStatus.ADMIN);
        member.setCreateDate(LocalDateTime.now());

        return member;
    }
    public void updateMember(String email, String password, String name, String nickname, String address){
        this.email = email;
        this.password =password;
        this.name =name;
        this.nickname = nickname;
        this.address = address;
    }

}
