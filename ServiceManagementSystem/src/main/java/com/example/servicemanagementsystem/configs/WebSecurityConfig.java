package com.example.servicemanagementsystem.configs;
import com.example.servicemanagementsystem.services.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    @Autowired
    private JwtRequestFilter requestFilter;

      /*  @Bean
       public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .cors(cors -> cors.configurationSource(corsConfigurationSource())) // New syntax for enabling CORS
                    .csrf(csrf -> csrf.disable()) // Disable CSRF
                    .authorizeHttpRequests(auth -> auth
                            .anyRequest().permitAll() // Customize your authorization here
                    );
            return http.build();
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
            return http.csrf().disable()
                    .authorizeHttpRequests()
                    .requestMatchers("/authenticate","/company/sign-up","/client-signup","/ads","/search/{service}").permitAll()
                    .and()
                    .authorizeHttpRequests().requestMatchers("/api/**")
                    .authenticated().and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class)
                    .build();
        }*/
     @Bean
      public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
          http
                  .csrf(csrf -> csrf.disable())
                  .authorizeHttpRequests(auth -> auth
                          .requestMatchers("/authenticate", "/company/sign-up", "/client/sign-up", "/ads", "/search/{service}").permitAll()
                          .requestMatchers("/api/**").authenticated()
                  )
                  .sessionManagement(session -> session
                          .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                  )
                  .addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class);

          return http.build();
      }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*")); // Allow Angular frontend
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allow methods
        configuration.setAllowedHeaders(Arrays.asList("*")); // Allow headers
        configuration.setAllowCredentials(true); // Allow credentials

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Apply to all endpoints
        return source;
    }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
        {
            return config.getAuthenticationManager();
        }

        @Bean
        public PasswordEncoder passwordEncoder(){
          return new BCryptPasswordEncoder();

    }


    }

