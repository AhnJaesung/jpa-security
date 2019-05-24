package com.ese.webservice.config;

import com.ese.webservice.member.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // TO DO
    // 1. 로그인,로그아웃의 권한에 따른 경로 설정
    // 2. DB 사용자 정보 로그인 처리
    // 3. Remember Me 사용
    // 4. 패스워드 암호화

    public static final String REMEMBER_ME_KEY = "rememberkey";

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    // 패스워드 인코더
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // auth userDetailService 연결
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    // 항상 허용가능한 경로
    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/add/**");
    }

    // 경로 관리
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                // 접근 옵션
                .authorizeRequests()
                    .antMatchers("/user/**").hasRole("USER")
                    .antMatchers("/**").permitAll()
                    .and()

                // 로그인관련
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/loginCheck")
                    .defaultSuccessUrl("/user")
                    .permitAll()
                    .failureUrl("/login")
                    .and()

                // 로그아웃 관련
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/home")
                    .permitAll()
                    .and()

                // 자동 로그인
                .rememberMe()
                    .key(REMEMBER_ME_KEY)
                    //.rememberMeServices(tokenBasedRememberMeServices()) Token based
                    .rememberMeParameter("remember")
                    .tokenValiditySeconds(86400)
                    .and()

                // 403 페이지 핸들러
                .exceptionHandling().accessDeniedPage("/403");

    }

    // Token based Remember Me
    @Bean
    public TokenBasedRememberMeServices tokenBasedRememberMeServices(){
        TokenBasedRememberMeServices tokenBasedRememberMeServices =
                new TokenBasedRememberMeServices(REMEMBER_ME_KEY, userDetailsService);
        tokenBasedRememberMeServices.setCookieName("loginCookie");
        return tokenBasedRememberMeServices;
    }

    // DB연동 Remember Me 추가예정
}
