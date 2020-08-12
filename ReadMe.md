# Spring Boot MySQL JWT Authentication

|API endpoint     | request-method | request-body |
|-----------------|--------|--------|
| `/authenticate` | POST | <code> { "username": "johndoe98", "password":"your^Pass#123" } </code> |
| `/register`     | POST | <code> {"username": "johndoe98", "firstName": "john", "lastName": "doe", "password":"your^Pass#123", "email":"vardhansahani@domain.com", "homeAddress":"Mumbai, Maharashtra.", "officeAddress":"Thane, Maharashtra.", "contact": "9999999999" } </code> |
| `/user`         | POST | <code> {"Authorization": "Bearer \<jwt-token-from-authentication\>"} </code>  |

- Start Database server.
- Configure [applicaton.properties](https://github.com/apache15/AuthSpringBootMySQL/blob/master/src/main/resources/application.properties)
- Clone [current repository](https://github.com/apache15/AuthSpringBootMySQL/)
- cd to root of repository
- Run command
    ```
    $ ./mvnw clean spring-boot:run
    ```
    
> Similarly it also provides functionality for checking username if already exists and update user details.

<pre>
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private UserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// We don't need CSRF for this example
		httpSecurity.cors().and().csrf().disable()
				.authorizeRequests().antMatchers("/authenticate", "/register")
				.permitAll().antMatchers(HttpMethod.OPTIONS, "/**")
				.permitAll().
				anyRequest().authenticated().and().
				exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

}
</pre>
