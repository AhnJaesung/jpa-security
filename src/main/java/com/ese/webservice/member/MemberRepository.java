package com.ese.webservice.member;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query(value = "SELECT * FROM MEMBER WHERE id = :id AND pwd = :pwd", nativeQuery = true)
    Member loginCheck(@Param(value = "id") String id, @Param(value = "pwd") String pwd);

    Member findByUemail(String email);

}
