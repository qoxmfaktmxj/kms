package com.app.kms.member.repository;

import com.app.kms.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    // 이메일로 회원정보 조회 (select * from member_table where member_email = ?)
    Optional<MemberEntity> findByMemberEmail(String memberEmail);//Optional : Null 방지
}
