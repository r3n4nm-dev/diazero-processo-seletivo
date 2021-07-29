package com.renanmateus.diazero.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.httpBasic()
		.and()
		.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/incidents", "incidents/").hasAnyRole("ADMIN", "USER")
		.antMatchers(HttpMethod.GET, "/incidents", "incidents/*").permitAll()
		.antMatchers(HttpMethod.PUT, "/incidents/update/*", "/incidents/close/*").hasAnyRole("ADMIN", "USER")
		.antMatchers(HttpMethod.DELETE, "/incidents/*" ).hasRole("ADMIN")
		.anyRequest().permitAll().and().csrf().disable()
		.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.inMemoryAuthentication()
		.withUser("admin")
		.password("{noop}admin")
		.roles("ADMIN")
		.and()
		.withUser("user")
		.password("{noop}user")
		.roles("USER");
		
	}
	
}
