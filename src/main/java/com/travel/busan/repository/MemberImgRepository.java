package com.travel.busan.repository;

import com.travel.busan.dto.MemberImgDto;
import com.travel.busan.entity.Member;
import com.travel.busan.entity.MemberImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberImgRepository extends JpaRepository<MemberImg, Long> {
    MemberImg findByMember(Member member);
    Optional<MemberImg> findById(Long id);
}
