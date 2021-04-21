package com.hehocom.hehocom.security.configuration;

import static com.hehocom.hehocom.security.SecurityConstants.LOG_IN_URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

import com.hehocom.hehocom.security.filter.JWTAuthorizationFilter;
import com.hehocom.hehocom.services.MemberService;

@EnableWebSecurity
public class WebSecurity extends AbstractConfiguration {

	// ###########################################################################
	// WebSecurity boolean toggle for HTTP Pattern Matcher enablement.
	// To be modified in application.properties file :
	// - for security enabled (default or empty/null/commented):
	// api.security.httpPatternMatcher.disabled=false
	// - for security disabled :
	// api.security.httpPatternMatcher.disabled=true
	// ###########################################################################

	@Value("${api.security.httpPatternMatcher.disabled:false}")
	private boolean httpPatternMatcherDisabled;

	@Autowired
	private MemberService userService;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll();
		if (!httpPatternMatcherDisabled) { // http pattern matcher enabled
			http.authorizeRequests().antMatchers(HttpMethod.POST, LOG_IN_URL, "/hehocom/whoami", "/hehocom/login")
					.permitAll()
					.antMatchers(HttpMethod.GET, "/favicon.ico", "/v2/api-docs", "/configuration/ui",
							"/swagger-resources/**", "/configuration/security", "/swagger-ui.html", "/webjars/**",
							"/h2/**", "hehocom/*")
					.permitAll().anyRequest().authenticated();
		} else { // http pattern matcher disabled
			http.authorizeRequests().anyRequest().permitAll(); // toutes les pages/requêtes sont accessibles
		}

		http.authorizeRequests().and().addFilter(new JWTAuthorizationFilter(authenticationManager()))
				// this disables session creation on Spring Security
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().headers()
				.frameOptions().disable();

		http.headers().contentTypeOptions();
		http.headers().xssProtection();
		http.headers().cacheControl();
		http.headers().httpStrictTransportSecurity();
		http.headers().frameOptions();
		http.headers().addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy", "script-src 'self'"));
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(s -> this.userService.getMemberByEmail(s))
				.passwordEncoder(this.bCryptPasswordEncoder());
	}

}