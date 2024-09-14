package com.polarbookshop.edgeservice.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import reactor.core.publisher.Mono;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.oidc.web.server.logout.OidcClientInitiatedServerLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.HttpStatusServerEntryPoint;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;
import org.springframework.security.web.server.csrf.CsrfToken;
import org.springframework.web.server.WebFilter;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http, ReactiveClientRegistrationRepository clientRegistrationRepository) {
        return http
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/","/*.css","/*.js","favicon.ico").permitAll()
                        .pathMatchers(HttpMethod.GET,"/books/**").permitAll()
                        .anyExchange().authenticated()) // 모든 요청에 대해 인증이 이뤄져야함.
                .exceptionHandling(exceptionHandlingSpec ->
                        exceptionHandlingSpec.authenticationEntryPoint(new HttpStatusServerEntryPoint(HttpStatus.UNAUTHORIZED)))
                .oauth2Login(Customizer.withDefaults()) //Oauth2/오픈ID커넥트 방식을 이용한 로그인 인증 진행
                .logout(logout -> logout.logoutSuccessHandler(oidcLogoutSuccessHandler(clientRegistrationRepository)))
                .csrf(csrf -> csrf.csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse())
                        )
                .build();
    }

    private ServerLogoutSuccessHandler oidcLogoutSuccessHandler(ReactiveClientRegistrationRepository clientRegistrationRepository) {
        var oidcLogoutSuccessHandler = new OidcClientInitiatedServerLogoutSuccessHandler(clientRegistrationRepository);
        oidcLogoutSuccessHandler.setPostLogoutRedirectUri("{baseUrl}"); // OIDC 공급자(키클록)에서 로그아웃 후 사용자를 스프링에서 동적으로 지정하는 애플리케이션 베이스 URL로 리다이렉션한다. 로컬=localhost:9000
        return oidcLogoutSuccessHandler;
    }



    // CsrfToken 리액티브 스트림을 구독하고 이 토큰 값을 올바르게 추출하기 위한 목적만을 갖는 필터
    @Bean
    WebFilter csrfWebfilter(){
        // Required because of https://github.com/spring-projects/spring-security/issues/5766
        return (exchange, chain) -> {
            exchange.getResponse().beforeCommit(() -> Mono.defer(() -> {
                Mono<CsrfToken> csrfTokenMono = exchange.getAttribute(CsrfToken.class.getName());

                if (csrfTokenMono != null) {
                    csrfTokenMono.subscribe(csrfToken -> {
                        // CSRF 토큰의 실제 값을 출력
                        System.out.println("CSRF Token Header Name: " + csrfToken.getHeaderName());
                        System.out.println("CSRF Token Parameter Name: " + csrfToken.getParameterName());
                        System.out.println("CSRF Token Value: " + csrfToken.getToken());
                    });
                }

                return csrfTokenMono != null ? csrfTokenMono.then() : Mono.empty();
            }));

            return chain.filter(exchange);
        };
    }
}
