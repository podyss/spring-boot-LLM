//package cn.edu.ncut.cs.springboot.llm.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig {
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        // 配置请求授权
////        http.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated());
//
//        // 表单登录配置
////        http.formLogin(form -> {
////            form.loginPage("/login").permitAll()
////                    .usernameParameter("username")
////                    .passwordParameter("password")
////                    .failureUrl("/login?failure");
//////                    .successHandler(new MyAuthenticationSuccessHandler())
//////                    .failureHandler(new MyAuthenticationFailureHandler());
////        });
//
//        // 注销处理
////        http.logout(logout -> {
////            logout.logoutSuccessHandler(new MyLogoutSuccessHandler());
////        });
//
//        // 异常处理
////        http.exceptionHandling(exception -> {
////            exception.authenticationEntryPoint(new MyAuthenticationEntryPoint());
////        });
//
//        // 解决跨域问题
//        http.cors(withDefaults());
//
//        // 关闭 CSRF 防护（根据项目需求决定是否启用）
////        http.csrf(csrf -> csrf.disable());
//
//        http.httpBasic(withDefaults());
//        return http.build();
//    }
//}
