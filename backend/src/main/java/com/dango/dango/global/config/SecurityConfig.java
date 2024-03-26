package com.dango.dango.global.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.dango.dango.global.filter.JwtFilter;

import lombok.RequiredArgsConstructor;

@Configuration
// @EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfig {
	private final JwtFilter jwtFilter;
	private final CorsConfig corsConfig;
	private static final String[] PERMIT_URL_ARRAY = {
		/* swagger v3 */
		"/v3/api-docs/**",
		"/swagger-ui/**"
	};

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		httpSecurity
			.sessionManagement(
				(session)-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			)
			.csrf(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests(
				auth -> auth
					.requestMatchers(HttpMethod.POST,"/api/user/**").permitAll()
					.requestMatchers(HttpMethod.GET,"/api/user/**").permitAll()
					.requestMatchers(PERMIT_URL_ARRAY).permitAll()
					.requestMatchers(HttpMethod.GET,"/error").permitAll()
					.requestMatchers(HttpMethod.POST,"/error").permitAll()
					.anyRequest().authenticated()
			).cors(
				cors -> cors.configurationSource(corsConfig.corsConfigurationSource())
			)
			.addFilterBefore(
				jwtFilter, UsernamePasswordAuthenticationFilter.class
			);

		return httpSecurity.build();
	}
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

}
