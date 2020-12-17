package com.Ampara.LifeMatch.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.Ampara.LifeMatch.model.UsuarioModel;

public class UserDetailsImpl implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String password;
	private List<GrantedAuthority> authorities;
	
	
	//CONSTRUTOR PADRÃO VAZIO
	public UserDetailsImpl() {
	}
	
	//CONSTRUTOR DEFININDO PARAMETROS
	public UserDetailsImpl(UsuarioModel user) {
		this.userName = user.getUsuario();
		this.password = user.getSenha();
	}
	
	
	//AQUI SÃO MÉTODOS QUE TEMOS QUE IMPLEMENTAR OBRIGATÓRIAMENTE POR CAUSA DO IMPLEMENTS
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	
	//ESTE MÉTODO RESGATA A SENHA DO USUÁRIO DO TOKEN
	@Override
	public String getPassword() {
		return password;
	}

	//ESTE MÉTODO RESGATA A O NOME DO USUÁRIO DO TOKEN
	@Override
	public String getUsername() {
		return userName;
	}

	//BOOLEANO QUE INFORMA SE A SENHA EXPIROU OU NÃO
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//BOOLEANO QUE INFORMA SE A CONTA ESTÁ DESBLOQUEADA
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//BOOLEANO QUE INFORMA SE A CREDENCIAL NÃO ESTÁ EXPIRADA
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//BOOLEANO QUE INFORMA SE ESTÁ ATIVO
	@Override
	public boolean isEnabled() {
		return true;
	}

	
	
}
