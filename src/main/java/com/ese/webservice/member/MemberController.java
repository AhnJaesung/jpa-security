package com.ese.webservice.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
public class MemberController {

    // 회원가입 : BCryptPasswordEncoder 암호화
    // 로그인 : Security사용 권환부여

    @Autowired
    MemberService memberService;

    /**
     * Member 추가
     * @param member
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String create(Member member, @RequestParam(value = "role") String role){
        // 패스워드 암호화 및 DB등록
        BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
        member.setUpw(pe.encode(member.getUpw()));
        memberService.save(member);

        // 룰 등록
        MemberRole r = new MemberRole();
        r.setRoleName(role);
        r.setUid((int)(long)member.getId());
        member.setRoles(Arrays.asList(r));
        memberService.saveRole(r);

        // SecurityContextHolder에서 Context를 받아 인증 설정
        SecurirtyMember userDetails = new SecurirtyMember(member);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/user";
    }

    /**
     * Login 페이지 이동
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }

    /**
     * Home으로 이동
     * @return
     */
    @RequestMapping(value = {"/home", "/"})
    public String home(){
        return "home";
    }

    /**
     * 회원가입으로 이동
     * @return
     */
    @RequestMapping(value = "/registerForm")
    public String register(){
        return "registerForm";
    }

    /**
     * User페이지 이동
     * @return
     */
    @RequestMapping(value = "/user")
    public String user(){
        return "user";
    }

    /**
     * 403에러 페이지 핸들러
     * @return
     */
    @RequestMapping(value = "/403")
    @ResponseBody
    public String page403(){
        return "접근이 거부되었습니다.";
    }

}
