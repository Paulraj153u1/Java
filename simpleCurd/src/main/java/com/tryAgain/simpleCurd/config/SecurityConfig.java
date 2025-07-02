package com.tryAgain.simpleCurd.config;

import com.tryAgain.simpleCurd.security.JwtFilter;
import com.tryAgain.simpleCurd.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auths->
            auths.requestMatchers(HttpMethod.POST,"/api/users").permitAll()
                .requestMatchers("/api/users/**").authenticated()
                .requestMatchers("/").permitAll()
                .anyRequest().permitAll()
        )
//                .formLogin(form-> form.permitAll().defaultSuccessUrl("/dashboard"))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        ;
      return  http.build();
    }
@Bean
    public UserDetailsService userDetailsService() {
//        UserDetails user = User.withUsername("alex").password(passwordEncoder.encode("alex@123")).roles("USER").build();
//        UserDetails admin = User.withUsername("paul").password(passwordEncoder.encode("paul@123")).roles("ADMIN").build();
//        return new InMemoryUserDetailsManager(user, admin);


    return new CustomUserDetailsService();
    }

    @SuppressWarnings("deprecation")
    @Bean
    public  DaoAuthenticationProvider  authenticationProvider()
    {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
    }


    @Bean
    public  AuthenticationManager  authenticationManager()  {
     return new ProviderManager(List.of(authenticationProvider()));
    }

}
