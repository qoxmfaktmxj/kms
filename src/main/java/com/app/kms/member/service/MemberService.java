package com.app.kms.member.service;

import com.app.kms.member.dto.MemberDTO;
import com.app.kms.member.entity.MemberEntity;
import com.app.kms.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service //Spring이 관리해주는 객체 , Spring Bean으로 등록
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public void save(MemberDTO memberDTO) {
        // 1. dto -> entity 변환
        // 2. repository의 save 메서드 호출
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO); //alt Enter 좌변 만들어줌
        memberRepository.save(memberEntity); //save 는 JPA가 만들어줌 save호출로 Spring DATA JPA가 Insert 쿼리 만들어서 Table에 추가
        // repository의 save메서드 호출 (조건. entity객체를 넘겨줘야 함)

    }

    public MemberDTO login(MemberDTO memberDTO){
        /**
         * 1.회원이 입력한 이메일로 DB에서 조회
         * 2.DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
         */
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if(byMemberEmail.isPresent()) {
            // 조회 결과가 있는 경우 (해당 이메일을 가진 회원 정보가 있음)
            MemberEntity memberEntity = byMemberEmail.get();
            if (memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())) {
                // 비밀번호 일치
                // entity -> dto 객체 변환 후 리턴
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            } else {
                // 비밀번호 불일치
                return null;
            }
        } else {
            //조회 결과가 없다 (해당 이메일을 가진 회원이 없다)
            return null;
        }
    }
}
