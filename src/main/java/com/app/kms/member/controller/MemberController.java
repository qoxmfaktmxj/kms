package com.app.kms.member.controller;

import com.app.kms.member.dto.MemberDTO;
import com.app.kms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MemberController {
    // 생성자 주입
    private final MemberService memberService; //@RequiredArgsConstructor

    // 회원가입 페이지 출력 요청 (모든 요청은 GET)
    @GetMapping("/member/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        //RequestParam에 담겨온 값을 String memberEmail에 옮겨 담는다.
        System.out.println("MemberController.save");//soutm
        System.out.println("memberDTO = " + memberDTO);//soutp
        //MemberService memberService = new MemberService(); //spring 이전 버전
        memberService.save(memberDTO);
        return "login";
    }

    @GetMapping("/member/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session){
        MemberDTO loginResult = memberService.login(memberDTO);
        if(loginResult != null){
            //login 성공
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            return "main"; //현재 여기까지 안옴 main까지 안오는 상태 06까지 청강함
        } else {
            //login 실패
            return "login";
        }

    }
}
