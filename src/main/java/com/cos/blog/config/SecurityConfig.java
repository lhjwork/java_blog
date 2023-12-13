package com.cos.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // IoC
public class SecurityConfig {

 @Bean // IoC가 된다!
 BCryptPasswordEncoder encodePWD() {
	 // security가 들고 있는 함수
  return new BCryptPasswordEncoder();
 }
 
 // 시큐리티가 대신 로그인해주는데 password를 가로채기를 하는데
 // 해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
 // 같은 해쉬오 암호화해서 DB에 잇는 해쉬랑 비교할 수 있음.
 
 

 @Bean
 SecurityFilterChain configure(HttpSecurity http) throws Exception {
  http.csrf().disable() // csrf 토큰 비활성화 (테스트시 걸어두는 게 좋음)
    .authorizeRequests()
    .antMatchers("/","/auth/**", "/js/**", "/css/**", "/image/**")
    .permitAll()
    .anyRequest()
    .authenticated()
    .and()
    .formLogin()
    .loginPage("/auth/loginForm")
    .loginProcessingUrl("/auth/loginProc")
    .defaultSuccessUrl("/"); // 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인을 해준다.  
  
  return http.build();
 }
}