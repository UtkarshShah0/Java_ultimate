package com.boot.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

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
		http.formLogin(Customizer.withDefaults());
		http.httpBasic(Customizer.withDefaults());
		
		http.sessionManagement(session -> 
		session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));
		
		// when session is stateless dont enable formlogin, in browser when we access irl
		//browser will popup login dialog
		return http.build();
	}
	
	
    @Bean
    public UserDetailsService userDetailsService() {
            UserDetails user = User.withDefaultPasswordEncoder()
                    .username("testuser")
                    .password("testpassword")
                    .roles("USER")
                    .build();
            
            UserDetails user2 = User.withDefaultPasswordEncoder()
                    .username("james")
                    .password("bond")
                    .roles("ADMIN")
                    .build();
            
            return new InMemoryUserDetailsManager(user, user2);
    }
}













