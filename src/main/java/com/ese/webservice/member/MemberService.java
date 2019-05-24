package com.ese.webservice.member;

import java.util.List;
import java.util.Map;

public interface MemberService {

    int save(Member member);

    Member findByUemail(String email);

    List<MemberRole> findAllByUid(int uid);

    int saveRole(MemberRole r);

    /*int loginCheck(Map<String, String> param);*/

}
