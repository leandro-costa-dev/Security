package com.security.configuration;

import com.security.security.JwtAuthFilter;
import com.security.security.JwtService;
import com.security.service.UsuarioService;
import com.security.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OncePerRequestFilter jwtFilter() {
        return new JwtAuthFilter(jwtService, usuarioServiceImpl);
    }

    @Autowired
    private JwtService jwtService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
         //Autenticação em memória
//                auth.inMemoryAuthentication()
//                .passwordEncoder(passwordEncoder())
//                .withUser("teste")
//                .password(passwordEncoder().encode("teste"))
//                .roles("ADMIN");
                    auth.userDetailsService(usuarioServiceImpl)
                .passwordEncoder(passwordEncoder());
    }

//    @Override
//    public void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/api/buscar")
//                .hasAnyRole("ADMIN")
//                .antMatchers("/api/listar")
//                .hasAnyRole("USER", "ADMIN")
//                .and()
//                .httpBasic();
//    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/buscar")
                .hasAnyRole("ADMIN")
                .antMatchers("/api/listar")
                .hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui",
                "/swagger-resources/**", "/configuration/security",
                "/swagger-ui.html", "/webjars/**", "/api/auth");

    }

}
