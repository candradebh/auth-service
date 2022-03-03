package com.carlosandrade.microservice.auth;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carlosandrade.microservice.auth.model.Usuario;
import com.carlosandrade.microservice.auth.repository.UsuarioRepository;

@SpringBootApplication
@EnableAuthorizationServer
@EnableResourceServer
@RestController
public class AuthApplication {
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public CommandLineRunner init(@Autowired UsuarioRepository usuarioRepository) {
		return args -> {
			Usuario usuario = new Usuario();
			usuario.setNome("Carlos");
			usuario.setEmail("dev_carlos@outlook.com");
			usuario.setSenha(passwordEncoder.encode("cm2209"));
			usuarioRepository.save(usuario);	
		};
	}
	
	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

	
	
}
