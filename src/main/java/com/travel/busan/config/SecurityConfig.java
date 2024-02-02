package com.travel.busan.config;

import com.travel.busan.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private MemberService memberService;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.formLogin(form -> form
                .loginPage("/members/login")
                .defaultSuccessUrl("/") //로그인 성공시
                .usernameParameter("email") // 로그인시 사용할 파라미터 email로 설정.
                   .failureUrl("/members/login/error") // 실패시 url
        );

        http.logout(form -> form
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                .logoutSuccessUrl("/")
        );

        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    // 하위 파일은 인증을 무시하도록 설정
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web)-> web.ignoring().requestMatchers("/css/**", "/js/**", "/image/**");
    }



}
