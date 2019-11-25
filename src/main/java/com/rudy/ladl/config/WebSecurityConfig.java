package com.rudy.ladl.config;

import com.rudy.ladl.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    private final DataSource dataSource;

    @Autowired
    public WebSecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService, DataSource dataSource) {
        this.userDetailsService = userDetailsService;
        this.dataSource = dataSource;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    //TODO logout with csrf token
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .mvcMatchers("/admin", "/users").hasRole("ADMIN")
                    .mvcMatchers(Constant.SITE_ADD_OFFICIAL_TAG_PATH).hasRole("MEMBER")
                    .mvcMatchers(Constant.LOGIN_PATH, "/resources/*", "/image/*", "/css/*", "/js/*", Constant.HOME_PATH, Constant.REGISTRATION_PATH, Constant.SITES_PATH, Constant.SITES_PATH + "/*").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage(Constant.LOGIN_PATH)
                    .defaultSuccessUrl(Constant.HOME_PATH)
                    .failureUrl(Constant.LOGIN_PATH + "?error")
                .and()
                    .logout(logout ->
                            logout
                                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).deleteCookies("JSESSIONID"))

                .rememberMe().key("LADLRMBRKEY").tokenValiditySeconds(15 * 24 * 60 * 60)
        .and()
        .csrf().disable()
        ;
    }

    private PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
        tokenRepositoryImpl.setDataSource(dataSource);
        return tokenRepositoryImpl;
    }
}