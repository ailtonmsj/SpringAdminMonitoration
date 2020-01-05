package br.com.amsj.adminmonitoration.conf.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import de.codecentric.boot.admin.server.config.AdminServerProperties;

@EnableWebSecurity//(debug = true)
@Configuration(proxyBeanMethods = false)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private final AdminServerProperties adminContextPath;
	
    public SecurityConfiguration(AdminServerProperties adminServerProperties) {
        this.adminContextPath = adminServerProperties;
    }
    
    // Authorization
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(this.adminContextPath.path("/"));
		
		http.authorizeRequests()
			.antMatchers(this.adminContextPath.path("/assets/**")).permitAll()
			.antMatchers(this.adminContextPath.path("/login")).permitAll()
			.antMatchers(this.adminContextPath.path("/logout")).permitAll()
			.antMatchers(this.adminContextPath.path("/error")).permitAll()
			.anyRequest().authenticated()
			.and()
		.formLogin().loginPage(this.adminContextPath.path("/login")).successHandler(successHandler).and()
		.logout().logoutUrl(this.adminContextPath.path("/logout")).and()
		.httpBasic().and()
		.csrf()
        	.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())  
        	.ignoringRequestMatchers(
                new AntPathRequestMatcher(this.adminContextPath.path ("/instances"), HttpMethod.POST.toString()),  
                new AntPathRequestMatcher(this.adminContextPath.path("/instances/*"), HttpMethod.DELETE.toString()),  
                new AntPathRequestMatcher(this.adminContextPath.path("/actuator/**"))  
              );
	}
	
	// Authentication
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// Using BCryptPasswordEncoder to decode the password, the value after "{bcrypt}" is the password "test" crypted by BCryptPasswordEncoder
		auth.inMemoryAuthentication().withUser("test1").password("{bcrypt}$2a$10$AvWTmO4nvRbx9IQTZhOSDe3Ej5SoBUF1nO1vE5mPwChvel8lFgLTu").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("test2").password("{bcrypt}$2a$10$AvWTmO4nvRbx9IQTZhOSDe3Ej5SoBUF1nO1vE5mPwChvel8lFgLTu").roles("USER");
		
	}

	// Static Resources
	@Override
	public void configure(WebSecurity web) throws Exception {
		
	}
	
	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("test"));
	}
}
