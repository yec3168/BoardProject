package com.travel.busan.repository;

import com.travel.busan.entity.MemberImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberImgRepository extends JpaRepository<MemberImg, Long> {
}
