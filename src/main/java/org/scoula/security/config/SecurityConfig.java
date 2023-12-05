package org.scoula.security.config;


import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Log4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 모두 허용
                .antMatchers("/test", "/test/all").permitAll()
                // 특정 역할에게만 허용
                .antMatchers("/test/admin").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/test/member").access("hasRole('ROLE_MEMBER')")
                // 로그인 사용자에게만 허용
                .antMatchers("/board/create", "/board/update", "/board/delete").authenticated();

        http.formLogin()										// 로그인 설정 시작
                .loginPage("/auth/login")					// 로그인 페이지 GET URL
                .loginProcessingUrl("/auth/login"	)	// 로그인 POST URL
                .defaultSuccessUrl("/");					// 로그인 성공시 이동할 페이지



    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        log.info("configure .........................................");

        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}1234")
                .roles("ADMIN");

        auth.inMemoryAuthentication()
                .withUser("member")
                .password("{noop}1234")
                .roles("MEMBER");
    }


}
