package NewSales.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain http(HttpSecurity http) throws Exception {
        http.cors()
                //.httpBasic()
                .and()
                .authorizeHttpRequests()
                .anyRequest().authenticated()
                .antMatchers("/clients/**").permitAll()


                .antMatchers(HttpMethod.POST, "/clients/save")
                .hasAnyRole("USER", "ADMIN")


                .antMatchers("/pedidos/**")
                .hasAnyRole("USER", "ADMIN")
                .antMatchers("/produtcs/**")
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();

        return http.build(); }



}

