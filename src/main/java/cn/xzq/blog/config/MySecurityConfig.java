//package cn.xzq.blog.config;
//
//import cn.xzq.blog.security.MyUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
///**
// * @program: security-01
// * @description:
// * @author: XZQ
// * @create: 2019-10-31 13:03
// * @EnableWebSecurity //代表开启 Spring Security 安全认证与授权
// **/
//@Configuration
//@EnableWebSecurity
//public class MySecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private MyUserDetailsService myUserDetailsService;
//
//    /**
//     * 密码加密
//     *
//     * @return
//     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    /**
//     * 定义用户认证规则
//     *
//     * @param auth
//     * @throws Exception
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()  //csrf不可用
//                .authorizeRequests()
//                .antMatchers("/static/**").permitAll() //访问允许静态文件
//                .antMatchers("/", "/register").permitAll() //允许访问首页和注册页
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().loginPage("/login").failureUrl("/login?error")//指定登录页和登录失败页
//                .defaultSuccessUrl("/users/index") //登录成功跳转页
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .and()
//                .logout().logoutSuccessUrl("/login").permitAll() //退出登录跳转页
//                .and()
//                .rememberMe() //remember me
//                .userDetailsService(myUserDetailsService)
//                .tokenValiditySeconds(24 * 60 * 60);//token有效期24h
//    }
//}
//
///*
//
//class MyPasswordEncoder implements PasswordEncoder {
//
//    @Override
//    public String encode(CharSequence charSequence) {
//        return charSequence.toString();
//    }
//
//    @Override
//    public boolean matches(CharSequence charSequence, String s) {
//        return s.equals(charSequence.toString());
//    }
//}*/
