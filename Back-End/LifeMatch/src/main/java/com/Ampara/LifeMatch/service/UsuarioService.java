package com.Ampara.LifeMatch.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Ampara.LifeMatch.model.UsuarioLogin;
import com.Ampara.LifeMatch.model.UsuarioModel;
import com.Ampara.LifeMatch.repository.UsuarioRepository;

@Service //INDICANDO AO SPRING QUE ESSA É UMA CLASSE DE SERVIÇO
public class UsuarioService {

	//INJETANDO USUARIORESPOSITORY, SERVE TANTO PARA CADASTRAR NOSSOS USUÁRIOS 
	//QUANTO PARA LOCALIZÁ-LOS NA NOSSA BASE DE DADOS E GERAR O TOKEN
	@Autowired
	private UsuarioRepository repository;
	
	//MÉTODO PARA CADASTRAR USUARIO
	public Optional<UsuarioModel> CadastrarUsuario(UsuarioModel usuario) {
		
		//CONDIÇÃO PARA NÃO DEIXAR CADASTRAR USUARIOS IGUAIS
		if(repository.findByUsuarioContainingIgnoreCase(usuario.getUsuario()).isPresent())
			return null;
		
		//INSTANCIANDO UM NOVO OBJETO DO TIPO BCRYPTPASSWORDENCODER E CHAMAMOS ELE DE ENCODER
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		//ENCRIPTOGRAFANDO A SENHA E COLOCANDO DENTRO DE UMA VARIAVEL DO TIPO STRING CHAMADA DE SENHAENCODER
		String senhaEncoder = encoder.encode(usuario.getSenha());
		
		//SUBTITUINDO A SENHA DO OBJETO PELA SENHA DA VARIAVEL ENCRIPTOGRAFADA
		usuario.setSenha(senhaEncoder);
		
		//SALVANDO A SENHA ENCRIPTOGRAFADA NO BANCO DE DADOS ATRAVES DO REPOSITORY
		return Optional.of(repository.save(usuario));
	}
	
	
	//MÉTODO LOGAR
	public Optional<UsuarioLogin> Logar(Optional<UsuarioLogin> user){
		
		//INSTANCIANDO UM OBJETO DO TIPO BCRYPTPASSWORDENCODER
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		Optional<UsuarioModel> usuario = repository.findByUsuarioContainingIgnoreCase(user.get().getUsuario());
		
		if (usuario.isPresent()) {
			
			//ENCODER.MATCHES, COMPARA A SENHA QUE O USUARIO DIGITOU E A SENHA QUE ESTÁ ENCRIPTOGRAFADA NO BANCO DE DADOS E DEVOLVE UM BOOLEAN
			if (encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
				
				//CONTATENANDO O USUARIO E SENHA COM (:) PARA GERAR UM TOKEN 'ENCODADO'
				String auth = user.get().getUsuario() + ":" + user.get().getSenha();
				
				//ENCODANDO O USUARIO E SENHA NO PADRAO US-ASCII E COLOCANDO EM UM ARRAY DE BYTES
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				
				//COLOCANDO TUDO ISSO EM UMA STRING COM O PREFIXO 'BASIC'
				String authHeader = "Basic " + new String(encodedAuth);
				
				user.get().setToken(authHeader);
				user.get().setNome(usuario.get().getNome());
				
				return user;
				
			}
		}
		return null;
	}
	
	
}
