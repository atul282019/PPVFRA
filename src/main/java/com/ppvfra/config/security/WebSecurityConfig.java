package com.ppvfra.config.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;




@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true,prePostEnabled = true)

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Autowired
    private UserDetailsService customUserDetailsService;
 
    @Autowired
    private DataSource dataSource;
 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
 
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
         .userDetailsService(customUserDetailsService)
         .passwordEncoder(passwordEncoder());
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .csrf().disable()
         .headers()
          .frameOptions().sameOrigin()
          .and()
            .authorizeRequests()
				.antMatchers("/error","/js/**","/resources/**","/templates/**","/report/**","/static/**", "/webjars/**","/assets/**").permitAll()
                .antMatchers("/","/api/getsidemenu","/ppvfra/getDistricts","/bin/captcha","/bin/validateCaptcha","/ppvfra/cinnumber","/ppvfra/company","/ppvfra/acronym","/ppvfra/checkmail","/ppvfra/checkmobile","/login","/register","/CompanyRegistration","/InstitutionRegistration","/ApplicantRegistration","/fotgotpassword","/create","/edit_product","/createEmployee","/product","/getmenu","/indextest","/newcompanyregistration","/newinstitutionregistration","/applicantregistrationmethod","/ppvfra/checkusername","/ppvfra/checkinstitute","/ppvfra/abbreviation","/ppvfra/forgetpassword","/ppvfra/gettypeline").permitAll()
                //.antMatchers("/admin/**").hasAnyRole("ADMIN","PPVFRA","VERIFYING","APPROVING","DUSTESTING")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/admin")
                //.failureUrl("/login?error")
                //start to save login failed status in database
                .failureUrl("/login?error=Invalid Username or Password.").failureHandler(customAuthenticationFailureHandler())
                // End to save login failed status in database
                .permitAll()
                .and()
            .logout()
             .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
             .logoutSuccessUrl("/login?logout")
             .deleteCookies("my-remember-me-cookie")
                .permitAll()
                .and()
             .rememberMe()
              //.key("my-secure-key")
              .rememberMeCookieName("my-remember-me-cookie")
              .tokenRepository(persistentTokenRepository())
              .tokenValiditySeconds(24 * 60 * 60)
              .and()
            .exceptionHandling()
              ;
    }
     
    PersistentTokenRepository persistentTokenRepository(){
     JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
     tokenRepositoryImpl.setDataSource(dataSource);
     return tokenRepositoryImpl;
    }
    // start used to save login failed attemp in database
    @Bean
    public AuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }
    // end used to save login failed attemp in database
}