package com.ese.webservice.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberService.findByUemail(username);
        if(member == null){
            throw new UsernameNotFoundException(username);
        }else{
            member.setRoles(memberService.findAllByUid((int)(long)member.getId()));
            for(MemberRole r : member.getRoles()){
                System.out.println("roles ==>> " + r.getRoleName());
            }
        }

        return new SecurirtyMember(member);
    }
}
