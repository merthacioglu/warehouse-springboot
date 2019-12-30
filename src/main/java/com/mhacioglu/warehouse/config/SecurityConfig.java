package com.mhacioglu.warehouse.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.NoOpPasswordEncoder;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)

@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	@Lazy
	private UserDetailsService userDetailsService;
	
	@Bean
	public BCryptPasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.authorizeRequests().antMatchers("/css/**").permitAll()
    
         .antMatchers("/signup").permitAll().anyRequest() // /signup url uzantisina herkes erisebilir
         
         .authenticated()
         .and()
         .formLogin()
         .loginPage("/login") // /login url uzantisina herkes ersiebilir.
         .failureUrl("/login?error") // loginde hata olursa
         .usernameParameter("username").passwordParameter("password")
         .permitAll()
         .and()
         .logout()
         .logoutUrl("/logout") // logout url'i
         .logoutSuccessUrl("/") // bu sayfaya yonlendirliir
         .permitAll();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(getEncoder()); 
		
		
	}
	
	
}