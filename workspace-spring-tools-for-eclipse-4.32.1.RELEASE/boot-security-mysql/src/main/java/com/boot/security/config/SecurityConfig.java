package com.boot.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired 
	MyUserDetailService userDetailsService;
	
	@Bean
	static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//    	    http.authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll());
//            return http.build();

//		http.authorizeHttpRequests((authorize) -> authorize.anyRequest());
		http.csrf(customizer -> customizer.disable());
//		http.authorizeHttpRequests(request -> request.anyRequest().authenticated());
//		http.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/**").hasRole("USER"));
		http.authorizeHttpRequests((authorize) -> 
		authorize.requestMatchers("/root/inbox").hasRole("USER"));
		http.authorizeHttpRequests((authorize) -> 
		authorize.requestMatchers("/root/management").hasRole("ADMIN"));
		http.authorizeHttpRequests((authorize) -> 
		authorize.requestMatchers("/root/all").permitAll());
		http.authorizeHttpRequests((authorize) -> 
		authorize.requestMatchers("/","/register/add/**").permitAll());
//		http.formLogin(Customizer.withDefaults());
		http.httpBasic(Customizer.withDefaults());
		
		http.sessionManagement(session -> 
		session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));
		
		// when session is stateless dont enable formlogin, in browser when we access irl
		//browser will popup login dialog
		
		// when we disable form login the access from postman is unaffected
		return http.build();
	}
	
	
	
    public UserDetailsService userDetailsService() {
    	return userDetailsService;
    }
//            UserDetails user = User.withDefaultPasswordEncoder()
//                    .username("testuser")
//                    .password("testpassword")
//                    .roles("USER")
//                    .build();
//            
//            UserDetails user2 = User.withDefaultPasswordEncoder()
//                    .username("james")
//                    .password("bond")
//                    .roles("ADMIN")
//                    .build();
//            
//            return new InMemoryUserDetailsManager(user, user2);
    
    
      @Bean
      AuthenticationProvider authenticationProvider() {
    	  DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    	  provider.setPasswordEncoder(passwordEncoder());
    	  provider.setUserDetailsService(userDetailsService);
    	  return provider;
      }
    
      @Bean
      public AuthenticationManager authenticationManager 
      (AuthenticationConfiguration authenticationConfiguration)
      	throws Exception {
    	  return authenticationConfiguration.getAuthenticationManager();
      }
    
}













