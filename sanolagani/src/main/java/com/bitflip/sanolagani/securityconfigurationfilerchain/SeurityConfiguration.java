package com.bitflip.sanolagani.securityconfigurationfilerchain;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SeurityConfiguration extends WebSecurityConfigurerAdapter{
	
@Override
protected void configure(HttpSecurity http) throws Exception {
	 http
	    .authorizeRequests()
	    .antMatchers("/login","/register").permitAll()
	    .anyRequest()
	    .authenticated()
	    .and()
	    .formLogin()
	    .loginPage("/login")
	    .defaultSuccessUrl("/home")
	    .failureUrl("/signup")
	    .usernameParameter("email")
	    .passwordParameter("password")
	    .permitAll()
	    .and()
	    .logout()
	    .logoutUrl("/logout")
	    .invalidateHttpSession(true)
	    .deleteCookies("JSESSIONID")
	    .and()
	    .exceptionHandling()
	    .and()
	    .csrf()
	    .disable()
	    .httpBasic();
}

}
