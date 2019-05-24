package com.ese.webservice.member;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SecurirtyMember extends User {

    private static final String ROLE_PREFIX = "ROLE_";
    private static final Long serialVersionUID = 1L;
    public String nick;

    // principal에서 사용할 수 있는 값들을 정의 및 매칭
    public SecurirtyMember(Member member){
        super(member.getUemail(), member.getUpw(), makeGrantedAuthority(member.getRoles()));
        this.nick = member.getUid();
    }

    private static List<GrantedAuthority> makeGrantedAuthority(List<MemberRole> roles){
        List<GrantedAuthority> list = new ArrayList<>();
        roles.forEach(role -> list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRoleName())));
        return list;
    }

}
