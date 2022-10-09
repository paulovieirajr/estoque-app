package dev.paulovieira.estoqueapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfiguration{

    private static final String ENCODED_PASSWORD = "$2a$10$tnWwkQ.Y7QIlmddFLFlu9ui2J6JZhc1A4IsNZNHUgG1CCnLS84IUu";

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        try {
            http
                    .authorizeRequests()
                        .antMatchers(HttpMethod.GET, "/nota-entrada", "/nota-saida", "/estoque")
                            .hasRole("ADMIN")
                    .anyRequest()
                        .authenticated()
                    .and()
                        .formLogin(form -> {
                                    try {
                                        form
                                                .loginPage("/login")
                                                .defaultSuccessUrl("/", true)
                                                .failureUrl("/login-error") // TODO: Implementar pagina de erro
                                                .permitAll()
                                        .and()
                                                .logout()
                                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                                .logoutSuccessUrl("/login");
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                }

                        );

            // Permitir acesso ao banco h2 com autenticação
            http.csrf().disable();
            http.headers().frameOptions().disable();

            return http.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    @Description("UserDetails utiliza os dados em memória no momento.")
    public UserDetailsService users() {
        UserDetails admin = User.builder()
                .username("admin")
                .password(ENCODED_PASSWORD)
                .roles("ADMIN")
                .build();

        UserDetails user = User.builder()
                .username("user")
                .password(ENCODED_PASSWORD)
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }


}
