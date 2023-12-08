package org.scoula.security.config;


import lombok.extern.log4j.Log4j;
import org.scoula.security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@Log4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);

        return repo;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);

        http.addFilterBefore(filter, CsrfFilter.class);

        // api 호출은 csrf token 무시
        http.csrf().ignoringAntMatchers("/api/**");

        http.authorizeRequests()
            // 모두 허용
            .antMatchers("/test", "/test/all").permitAll()
            // 특정 역할에게만 허용
            .antMatchers("/test/admin").access("hasRole('ROLE_ADMIN')")
            .antMatchers("/test/member").access("hasRole('ROLE_MEMBER')")
            // 로그인 사용자에게만 허용
            .antMatchers("/board/create", "/board/update", "/board/delete").authenticated();

        http.formLogin()							// 로그인 설정 시작
            .loginPage("/auth/login")				// 로그인 페이지 GET URL
            .loginProcessingUrl("/auth/login"	)	// 로그인 POST URL
            .defaultSuccessUrl("/");				// 로그인 성공시 이동할 페이지



        http.logout()								// 로그아웃 설정 시작
            .logoutUrl("/auth/logout")				// POST: 로그아웃 호출 url
            .invalidateHttpSession(true)			// 세션 invalidate
            .deleteCookies("remember-me", "JSESSION-ID")	// 삭제할 쿠키 목록
            .logoutSuccessUrl("/");					// 로그아웃 이후 이동할 페이지

        http.rememberMe()		// remember-me 기능 설정
                .key("Galapagos")
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(7*24*60*60);	// 7일

    }


    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsService customUserService() {
        return new CustomUserDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        log.info("configure .........................................");

//        auth.inMemoryAuthentication()
//                .withUser("admin")
////			.password("{noop}1234")		// {noop} 암호화처리 하지않음
//                .password("$2a$10$EsIMfxbJ6NuvwX7MDj4WqOYFzLU9U/lddCyn0nic5dFo3VfJYrXYC")	// 암호화된 비밀번호
//                .roles("ADMIN");
//
//        auth.inMemoryAuthentication()
//                .withUser("member")
////			.password("{noop}1234") 		// {noop} 암호화처리 하지않음
//                .password("$2a$10$EsIMfxbJ6NuvwX7MDj4WqOYFzLU9U/lddCyn0nic5dFo3VfJYrXYC") 	// 암호화된 비밀번호
//                .roles("MEMBER");
        auth
                .userDetailsService(customUserService())
                .passwordEncoder(passwordEncoder());

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
