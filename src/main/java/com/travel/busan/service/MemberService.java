package com.travel.busan.service;

import com.travel.busan.entity.Member;
import com.travel.busan.repository.MemberImgRepository;
import com.travel.busan.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional  // 에러가 발생하면 변경된 데이터를 에러가 발생하기 전으로 돌려줌.
public class MemberService implements UserDetailsService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    public MemberImgRepository memberImgRepository;

    public Member save(Member member){
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember != null)
            throw new IllegalStateException("이미 존재하는 회원입니다.");

       return memberRepository.save(member);
    }

    public Member memberView(Long id){
        return memberRepository.findById(id).get();
    }

    // 로그인시 이메일 매핑.
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member findEmail = memberRepository.findByEmail(email);

        if(findEmail == null)
            throw new UsernameNotFoundException(email);

        return User.builder()
                .username(findEmail.getEmail())
                .password(findEmail.getPassword())
                .roles(findEmail.getRoleStatus().toString())
                .build();
    }


}
