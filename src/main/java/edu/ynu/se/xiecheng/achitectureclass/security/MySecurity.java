package edu.ynu.se.xiecheng.achitectureclass.security;

import edu.ynu.se.xiecheng.achitectureclass.service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;
import java.util.Arrays;

@Configuration
@EnableWebSecurity //开启webSecurity服务
@CrossOrigin("*")
public class MySecurity {
    @Resource
    private MyUserDetailsService myUserDetailsService;
    @Resource
    private MyCustomSuccessHandler myCustomSuccessHandler;

    @Resource
    private JwtRequestFilter jwtRequestFilter;
    @Bean
    public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        //将编写的UserDetailsService注入进来
        provider.setUserDetailsService(myUserDetailsService);
        //将使用的密码编译器加入进来
        provider.setPasswordEncoder(passwordEncoder);
        //将provider放置到AuthenticationManager 中
        ProviderManager providerManager = new ProviderManager(provider);
        return providerManager;
    }

    /*
     * 在security安全框架中，提供了若干密码解析器实现类型。
     * 其中BCryptPasswordEncoder 叫强散列加密。可以保证相同的明文，多次加密后，
     * 密码有相同的散列数据，而不是相同的结果。
     * 匹配时，是基于相同的散列数据做的匹配。
     * Spring Security 推荐使用 BCryptPasswordEncoder 作为密码加密和解析器。
     * */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
     * 配置权限相关的配置
     * 安全框架本质上是一堆的过滤器，称之为过滤器链，每一个过滤器链的功能都不同
     * 设置一些链接不要拦截
     * */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//       关闭csrf

        httpSecurity.cors(cors -> cors.configurationSource(corsConfigurationSource()));
        httpSecurity.csrf(it->it.disable());
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.authorizeHttpRequests(it->
                it.antMatchers("/business/**")
                        .permitAll()
//                        .requestMatchers("/BusinessController/list").hasRole("基本权限")//设置登录路径所有人都可以访

                        .antMatchers("/customer/**","/shopImg/**","/foodImg/**")
                        .permitAll()
                        .antMatchers("/staticfiles/**")
                        .permitAll()
                        .anyRequest().authenticated()  //其他路径都要进行拦截
        );
// 配置表dfereff
        httpSecurity.sessionManagement(sessionManagement ->
                sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)// 如果需要则创建会话
                        .sessionConcurrency(concurrencyControl ->
                                concurrencyControl
                                        .maximumSessions(4) // 每个用户只允许有一个活跃会话
                                        .expiredUrl("/session-expired") // 会话过期时的跳转URL
                        )
        );

        httpSecurity.formLogin(form -> form
                .loginPage("http://localhost:5173/login")
                .usernameParameter("username")
                .passwordParameter("password")
                        .loginProcessingUrl("/customer/login")
//                        .defaultSuccessUrl("/BusinessController/getUserByIdByPass")
                        .successHandler(myCustomSuccessHandler).permitAll()
                // 使用自定义的认证成功处理器
        );
        httpSecurity
                .rememberMe(rememberMe -> rememberMe
                        .tokenValiditySeconds(86400 * 30) // 例如，设置为30天
                        .alwaysRemember(true) // 始终记住用户
                        .rememberMeCookieName("remember-me")
                        .useSecureCookie(false)// 设置cookie名称
                )
        ;
        return httpSecurity.build();

    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")); // 允许任何方法（http请求）
        configuration.setAllowedHeaders(Arrays.asList("*")); // 允许任何头
        configuration.setAllowCredentials(true); // 允许证书
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
