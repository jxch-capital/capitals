package io.github.jxch.capitals.stock4j.webapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        SpringUtil.getBean(OAuth2LoginAuthenticationFilter.class);
        // 配置 HttpSecurity
        http
                // 1. 会话管理：指定如何创建 HTTP 会话
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // 按需创建会话
                )

                // 2. 配置路径权限
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers( "/public/**").permitAll()  // 允许匿名访问的路径
                        .anyRequest().authenticated()                    // 其他所有请求需要认证
                )

                // 3. 配置 OAuth2 登录
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/oauth2/authorization/keycloak") // 重定向到 Keycloak 登录界面
                        .defaultSuccessUrl("/home", true)           // 登录成功后跳转到 /home 页面
                        .failureUrl("/login?error=true")            // 登录失败跳转地址
                )

                // 4. 配置退出登录
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout=true") // 退出登录后跳转到登录页
                        .permitAll()                           // 允许所有用户访问注销功能
                )

                // 5. 禁用 CSRF（根据实际需求）
                .csrf(AbstractHttpConfigurer::disable); // 如果是 API 应用程序，可以禁用 CSRF

        return http.build();
    }
}
