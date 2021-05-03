package com.green.jdevd010.CoffeeMintClient.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.green.jdevd010.CoffeeMintClient.controllers.services.UserDetailsServiceImpl;

@Configuration()
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//	private final String password = "$2a$10$QF/YQ9BzZoPmi5of5kkNeOP0bre/XmlsxyMX61aEeKwBsSxsfv1/u";

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean
	public UserDetailsService userDetailsService() {
		System.out.println("userDetailsService *********");
		return new UserDetailsServiceImpl();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

//	@Override
//	@Bean
//	protected UserDetailsService userDetailsService() {
//		UserDetails userAdmin = User.withUsername("admin")
//				.password(password).roles("ADMIN").build();
//		
//		UserDetails userGreen = User.withUsername("green")
//				.password(password).roles("USER").build();
//		
//		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//		
//		manager.createUser(userAdmin);
//		manager.createUser(userGreen);
//		
//		return manager;
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "/assets/**", "/css/**", "/fonts/**", "/images/**", "/js/**", "/vendor/**")
				.permitAll()
		.anyRequest().authenticated()
		.and().formLogin().permitAll();
//				.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and().logout()
//				.permitAll();
	}

}
