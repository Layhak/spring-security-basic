package co.istad.jbsdemo.spring_security_basic.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    //todo normally useDetailsService will get the UserDetails obj from the DB
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userDetails1 = User.
                builder().
                username("admin").
                password(passwordEncoder().
                        encode("admin")).
                roles("Admin").build();
        UserDetails userDetails2 = User.
                builder().
                username("normalUser").
                password(passwordEncoder().
                        encode("normal")).
                roles("User").build();

        UserDetails userDetails3 = User.
                builder().
                username("author").
                password(passwordEncoder().
                        encode("author")).
                roles("Author").
                build();
        return new InMemoryUserDetailsManager(userDetails1, userDetails2, userDetails3);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //?Optional
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    //?============================================================

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(
                        (auth) -> auth.requestMatchers("/register", "/delete/").
                                permitAll()
                                .requestMatchers("api/v1/admins/**").hasRole("Admin")
                                .requestMatchers(HttpMethod.GET, "api/v1/articles/**").hasAnyRole("Admin", "Author", "User")
                                .requestMatchers(HttpMethod.POST, "api/v1/articles/**").hasAnyRole("Admin", "Author")
                                .requestMatchers(HttpMethod.DELETE, "api/v1/articles/delete/**").hasAnyRole("Admin", "Author")
                                .anyRequest().authenticated()
                ).csrf(AbstractHttpConfigurer::disable).formLogin(AbstractHttpConfigurer::disable)//*disable default login form
                .httpBasic(Customizer.withDefaults())//*basic auth
                .build();
    }

}
