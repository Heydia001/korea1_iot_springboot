package org.example.springbootdeveloper.config;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

// 웹 보안 구성(설정)
@Configuration // 해당 클래스가 설정 클래스로 사용됨을 명시
@EnableWebSecurity // Spring Security의 웹 보안을 활성화
@RequiredArgsConstructor // final 필드 | @NonNull 필드에 대해 생성자를 자동 생성
public class WebSecurityConfig {

    private final UserService userService;

    @Bean
    // 정적 리소스나 특정 URL에 대해 Spring Security가 보안 검사를 무시하도록 설정
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                // H2 콘솔에 대한 접근 보안 검사를 무시하도록 설정
                .requestMatchers(toH2Console())
                // "/static/**" 경로의 정적 리소스를 보안 검사에서 제외
                .requestMatchers(new AntPathRequestMatcher("/static/**"));
    }

    // 보안 필터 체인 정의
    // : 특징 HTTP 요청에 대한 웹 기반 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                // 특정 경로에 대한 Access 설정
                                // .requestMatcher()
                                // : 특정 요청과 일치하는 url에 대한 엑세스
                                new AntPathRequestMatcher("/login"),
                                new AntPathRequestMatcher("/signup"),
                                new AntPathRequestMatcher("/user")
                        )
                        // .permitAll()은 누구나 접근이 가능하게 함
                        // /login, /signup, /user 요청이 오면 인증, 인가 없이도 접근 가능
                        .permitAll()
                        //.anyRequest()
                        // : 위에서 성정한 URL 이외의 요청에 대해 설정
                        // .authenticated()
                        // : 별도의 인가는 필요하지 않지만 인증이 성공 된 상태여야 접근이 가능하다.
                        .anyRequest().authenticated())
                // CF) csrf(Cross-site Request Forgery)
                //      : 사이트 간 요청 위조의 줄임말
                // csrf 공격을 방지하기 위해 활성화 하는 것을 권장
                .csrf(AbstractHttpConfigurer::disable) // 비활성화
                .build();
    }

    // 인증 처리
    // AuthenticationManager
    // : Spring Security에서 인증(Authentication)을 담당하는 핵심 인터페이스
    // >> 인증 과정에서 사용자 자격 증명(EX. username, password)을 확인하고
    //      , 올바르면 인증 토큰을 반환
    @Bean
    public AuthenticationManager authenticationManager(
            HttpSecurity http,
            // 비 크립트 패스워드 인코더
            BCryptPasswordEncoder bCryptpasswordEncoder,
            UserDetailsService userDetailsService,
            UserService userService
    ) throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(bCryptpasswordEncoder);
        return new ProviderManager(authProvider);
    }

    @Bean
    public BCryptPasswordEncoder bCryptpasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}