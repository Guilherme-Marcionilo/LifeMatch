package com.Ampara.LifeMatch.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Ampara.LifeMatch.model.UsuarioModel;
import com.Ampara.LifeMatch.repository.UsuarioRepository;

//CLASSE ENCARREGADA DE RECEBER NOSSO USUÁRIO E CONVERTER PARA USER DETAILS
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	//INJETANDO O REPOSITORY PORQUE TEMOS QUE LOCALIZA-LO NO BANCO DE DADOS E CONVERTE-LÔ EM USERDETAILS
	@Autowired
	private UsuarioRepository userRepository;
	
	
	//MÉTODO OBRIGATÓRIAMENTE IMPLEMENTADO POR CAUSA DO IMPLEMENTS
	//MAS NO CORPO ADICIONAMOS NOSSA LÓGICA
	//AQUI ESTAMOS PESQUISANDO UM USUÁRIO E RETONANDO ELE NO TIPO USERDETAILS
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		Optional<UsuarioModel> user = userRepository.findByUsuarioContainingIgnoreCase(userName);
		user.orElseThrow(() -> new UsernameNotFoundException(userName + " não encontrado."));
		
		return user.map(UserDetailsImpl::new).get();
	}

	
	
}
