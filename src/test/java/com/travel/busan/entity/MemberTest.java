package com.travel.busan.entity;

import com.travel.busan.dto.MemberFormDto;
import com.travel.busan.dto.MemberImgDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberTest {

    @PersistenceContext
    EntityManager em;

    public Member createMember(){
        MemberFormDto member = new MemberFormDto();
        member.setEmail("1@1");
        member.setPassword("123123123");
        member.setName("ㅇㄹ");
        member.setNickname("ㄴㄴㅇ");
        member.setAddress("ㅈㅅ");
        em.persist(member);

        MemberImgDto memberImg = new MemberImgDto();
        memberImg.setFileName("default.jpg");
        memberImg.setUrl("asdasd") ;

        em.persist(memberImg);

        return Member.createMember(m)
    }

    public void ImgTest(){
        Member member = createMember();

    }
}