package com.travel.busan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_img_id")
    private Long id;

    private String fileName;

    private String originName;

    private String url;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // Image update
    public void updateImg(String fileName, String originName, String url){
        this.fileName = fileName;
        this.originName =originName;
        this.url = url;
    }


}
