package br.com.amsj.adminmonitoration.conf.security;

import org.springframework.context.annotation.Configuration;

//@EnableWebSecurity
//@Configuration(proxyBeanMethods = false)
public class SecurityConfiguration{/* extends WebSecurityConfigurerAdapter {
	
	private final AdminServerProperties adminContextPath;
	
    public SecurityConfiguration(AdminServerProperties adminServerProperties) {
        this.adminContextPath = adminServerProperties;
    }
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(this.adminContextPath.path("/"));
		
		http.authorizeRequests()
		.antMatchers(this.adminContextPath.path("/assets/**")).permitAll()
		.antMatchers(this.adminContextPath.path("/login")).permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage(this.adminContextPath.path("/login")).successHandler(successHandler)
		.and()
		.logout().logoutUrl(this.adminContextPath.path("/logout"))
		.and()
		.httpBasic().and()
		.csrf()
        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())  
        .ignoringRequestMatchers(
                new AntPathRequestMatcher(this.adminContextPath.path ("/instances"), HttpMethod.POST.toString()),  
                new AntPathRequestMatcher(this.adminContextPath.path("/instances/*"), HttpMethod.DELETE.toString()),  
                new AntPathRequestMatcher(this.adminContextPath.path("/actuator/**"))  
              )
            .and()
            .rememberMe().key(UUID.randomUUID().toString()).tokenValiditySeconds(1209600);
        
        
//        .ignoringAntMatchers(
//        		this.adminContextPath + "/instances",   
//        		this.adminContextPath + "/actuator/**");  
		
//		.and().csrf().disable();
//		.addFilterBefore(new AuthorizationTokenFilter(usuarioRepository, tokenService), UsernamePasswordAuthenticationFilter.class); // Include the new Filter before the 
		
	}
	
	@Override
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
	  }
	  
	  */
}
