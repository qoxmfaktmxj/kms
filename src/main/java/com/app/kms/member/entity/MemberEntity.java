package com.app.kms.member.entity;

import com.app.kms.member.dto.MemberDTO;
import com.app.kms.member.service.MemberService;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "member_table")
public class MemberEntity {
    @Id // PK 지정
    @GeneratedValue(strategy =  GenerationType.IDENTITY) // auto_increment 지정해주는 부분 ( Oracle에서 SEQ )
    private Long id;

    @Column(unique = true) // unique 제약 조건 추가
    private String memberEmail; //camelCase 적용 시 DB 들어갈 떄 MEMBER_EMAIL

    @Column
    private String memberPassword;

    @Column
    private String memberName;

    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());//DTO에 담긴걸 Entity 객체로 넘김
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        return memberEntity;
    }
}
