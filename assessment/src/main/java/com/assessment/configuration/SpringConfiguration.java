package com.assessment.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.assessment.service.DefaultUserService;


@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringConfiguration {
	
	@Autowired
	DefaultUserService userDetailsService;
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userDetailsService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
	
//	@Bean
//	public InMemoryUserDetailsManager userDetailsService() {
//		UserDetails userX = User.withDefaultPasswordEncoder()
//				.username("userX")
//				.password("password")
//				.roles("PUBLISHER")
//				.build();
//		
//		UserDetails userR = User.withDefaultPasswordEncoder()
//				.username("userR")
//				.password("password")
//				.roles("REVIEWER")
//				.build();
//		
//		UserDetails userA = User.withDefaultPasswordEncoder()
//				.username("userA")
//				.password("password")
//				.roles("APPROVER")
//				.build();
//		return new InMemoryUserDetailsManager(userX,userR,userA);
//	}
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
				
		return http.csrf().disable().authorizeRequests()
				.requestMatchers("/list").hasAnyRole("PUBLISHER","REVIEWER", "APPROVER")
				.requestMatchers("/txn").hasAnyRole("PUBLISHER","REVIEWER", "APPROVER")
				.requestMatchers("/registration").permitAll()
				.requestMatchers("/genToken").permitAll()
				.anyRequest().authenticated().and().
				sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class)
				.build();
				
		
//		http.cors().and().csrf().disable().authorizeRequests().antMatchers("/registration","/genToken").permitAll().anyRequest()
//		.authenticated().and().sessionManagement()
//        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
// http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
//return http.build();
	}
	
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration autheticationConfiguration)
			throws Exception {
		return autheticationConfiguration.getAuthenticationManager();
	}

	@Bean
    public JwtFilter authenticationTokenFilterBean() throws Exception {
        return new JwtFilter();
    }
}
