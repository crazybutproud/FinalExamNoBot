package com.example.finalexam.configurations;

import com.example.finalexam.model.enumProperties.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final String[] breakPointsForAll = {"/","/login","/registration"};
    private final String[] breakPointsForAdmin ={"/admin/**"};
    private final String[] breakPointsForUser ={"/user/**"};

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() { //создали дефолтный.связь с бд
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(); //забирает данные из бд(добавили что нужно)
        daoAuthenticationProvider.setUserDetailsService(userDetailsService); // провайдер в бд.забирает данные и трансформирует в нужный объект(user)
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() { //шифрование пароля.будет возвращать
        return new BCryptPasswordEncoder(12);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { // связали с бд
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers(breakPointsForAll).permitAll()
                .antMatchers(breakPointsForUser).hasRole(Role.ROLE_USER.name().substring(5))
                .antMatchers(breakPointsForAdmin).hasRole(Role.ROLE_ADMIN.name().substring(5))
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/") /////////////////////////
                .and()
                .logout().permitAll().logoutSuccessUrl("/")
        ;

    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return  (web) -> web.ignoring()
                .requestMatchers(new AntPathRequestMatcher("/styles/*.css"))
                .requestMatchers(new AntPathRequestMatcher("/scripts/*.js"))
                .requestMatchers(new AntPathRequestMatcher("/images/**"));

    }

}
