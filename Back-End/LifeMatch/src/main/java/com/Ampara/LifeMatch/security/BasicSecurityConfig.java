package com.Ampara.LifeMatch.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


//Essa é uma classe de configuração, é ela que determina qual o tipo de criptografia será usada na senha,
//nesse caso usaremos o tipo de criptografia BASIC e também nessa classe que iremos configurar quais 
//endPoints estarão liberados para o usuario.

@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
		
		//aqui está permitindo para que todo mundo possa entrar nesses endPoints
		.antMatchers("/usuario/logar").permitAll()
		.antMatchers("/usuario/cadastrar").permitAll()
		
		//está restringindo o endpoint de postagem apenas para que possa ser feito GET
		//.antMatchers(HttpMethod.GET, "/postagem").permitAll() 
		
		//para os demais métodos desse endPoint tem que ser feita a autenticação
		.anyRequest().authenticated()
		.and().httpBasic()
		.and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().cors()
		.and().csrf().disable();
	}
}
