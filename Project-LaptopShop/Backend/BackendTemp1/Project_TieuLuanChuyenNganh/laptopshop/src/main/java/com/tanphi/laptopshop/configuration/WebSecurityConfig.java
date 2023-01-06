package com.tanphi.laptopshop.configuration;

import com.tanphi.laptopshop.security.JwtFilter;
import com.tanphi.laptopshop.security.oauth2.CustomOAuth2UserService;
import com.tanphi.laptopshop.security.oauth2.OAuth2SuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private JwtFilter jwtFilter;
	@Autowired
	OAuth2SuccessHandler oAuth2SuccessHandler;
	@Autowired
	CustomOAuth2UserService oAuth2UserService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.cors().and().csrf().disable().authorizeRequests().and().sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
//                .antMatchers("/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html#**","/webjars/**" ,"/swagger.json","/admin/auth/login","/auth/login","/registration/**,/**")
//                .permitAll()
//                    .antMatchers("/admin/**","/auth/**").hasAnyAuthority("ADMIN")
//                    .antMatchers("/account/**","/auth/**","/cart/**","/category/**","/address/**","/orders/**","/payment/**","view/product/**","/reviews/**","/wishlist/**").hasAnyAuthority("CUSTOMER")
//                .anyRequest().authenticated();

//				.antMatchers("/admin/**", "/registration/**", "/category/**", "/auth/**", "/swagger-ui.html#").permitAll()
//				.antMatchers("/admin/product/**").hasAnyAuthority("ADMIN")
//				.antMatchers("/product/**").hasAnyAuthority("CUSTOMER")
//				.anyRequest().authenticated()
//				.and()
//				.oauth2Login().authorizationEndpoint()
//				.baseUri("http://localhost:8080/login").and().userInfoEndpoint().userService(oAuth2UserService).and()
//				.successHandler(oAuth2SuccessHandler);
		http.cors().and().csrf().disable().authorizeRequests().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers("/admin/**", "/registration/**", "/category/**", "/**").permitAll()
				.anyRequest().authenticated().and().oauth2Login().authorizationEndpoint().baseUri("/oauth2/authorize").and()
				.userInfoEndpoint().userService(oAuth2UserService).and().successHandler(oAuth2SuccessHandler);
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
