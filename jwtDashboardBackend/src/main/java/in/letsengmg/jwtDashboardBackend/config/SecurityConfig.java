package in.letsengmg.jwtDashboardBackend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                // ✅ Spring Security 6 CORS FIX
                .cors(Customizer.withDefaults())

                // ❌ Disable CSRF for JWT APIs
                .csrf(csrf -> csrf.disable())

                // ✅ Authorization rules
                .authorizeHttpRequests(auth -> auth
                        // Allow preflight requests
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // Public endpoints
                        .requestMatchers(
                                "/auth/**",
                                "/oauth2/**",
                                "/forgot",
                                "/reset"
                        ).permitAll()

                        // Everything else requires auth
                        .anyRequest().authenticated()
                )

                // OAuth2 login success redirect
                .oauth2Login(oauth -> oauth
                        .defaultSuccessUrl("http://localhost:5173/dashboard", true)
                )

                // JWT filter
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
