package com.green.jdevd010.CoffeeMintClient.security;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.green.jdevd010.CoffeeMintClient.controllers.services.CustomerDetailServiceImpl;
import com.green.jdevd010.CoffeeMintClient.handlers.OnAuthenticationFailureHandler;
import com.green.jdevd010.CoffeeMintClient.handlers.OnAuthenticationSuccessHandler;
import com.green.jdevd010.CoffeeMintClient.handlers.OnLogoutSuccessHandler;
import com.green.jdevd010.CoffeeMintClient.handlers.OnOAuthenticationSuccess;

@Configuration()
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//	private final String password = "$2a$10$QF/YQ9BzZoPmi5of5kkNeOP0bre/XmlsxyMX61aEeKwBsSxsfv1/u";
	
	@Autowired
	private CustomOAuth2UserService customOAuth2UserService;
	
	@Autowired
	private OnOAuthenticationSuccess oauthenticationSuccess;
	
	@Autowired
	private OnLogoutSuccessHandler  logoutSuccessHandler;
	
	@Autowired
	private javax.sql.DataSource dataSource;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean
	public UserDetailsService userDetailsService() {
		System.out.println("userDetailsService *********");
		//return new UserDetailsServiceImpl();
		return new CustomerDetailServiceImpl();
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
	
	public PersistentTokenRepository getPersistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		
		tokenRepository.setDataSource(dataSource);
		
		return tokenRepository;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "/register", "/register/check_email", "/login2", "/login_error", "/assets/**", "/css/**", 
						"/fonts/**", "/images/**", "/js/**", "/vendor/**")
				.permitAll()
				.antMatchers("/update_product").hasAnyAuthority("product_manager")
				.antMatchers("/new_product").hasAnyAuthority("product_manager")
				.antMatchers("/delete_product").hasAnyAuthority("product_manager")
				.antMatchers("/update_order_status").hasAnyAuthority("saler", "shipper")
				.anyRequest().authenticated()
				//Oauth2
				.and().oauth2Login().loginPage("/login").permitAll()
				.userInfoEndpoint().userService(customOAuth2UserService)
				.and().successHandler(oauthenticationSuccess)
				//Form login
				.and().formLogin().loginPage("/login").permitAll()
				.usernameParameter("email")
				.passwordParameter("password")
				.loginProcessingUrl("/dologin")
				.failureHandler(new OnAuthenticationFailureHandler())
				.successHandler(new OnAuthenticationSuccessHandler())
				.and().logout().permitAll()
				//.logoutSuccessUrl("/")
				.logoutUrl("/dologout")
				.logoutSuccessHandler(logoutSuccessHandler)
				//.and().csrf().disable()
				//.and().rememberMe().key("jdevd0101greenacademy").tokenValiditySeconds(10*60)
				.and().rememberMe().tokenRepository(getPersistentTokenRepository())
				.and().exceptionHandling().accessDeniedPage("/403");

//Remember_me encrypt		
//		base64(username + ":" + expirationTime + ":" +
//		md5Hex(username + ":" + expirationTime + ":" password + ":" + key))
	}

}
