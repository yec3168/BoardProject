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


}