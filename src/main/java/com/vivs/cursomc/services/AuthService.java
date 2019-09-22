package com.vivs.cursomc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vivs.cursomc.domain.Cliente;
import com.vivs.cursomc.repositories.ClienteRepository;
import com.vivs.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private EmailService emailService;
	
	private Random rand = new Random();
	
	public void sendNewPassword(String email) {
		//buscar cliente por email para verificar se email existe
		Cliente cliente = clienteRepository.findByEmail(email);
		if(cliente == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}
		
		String newPass = newPassword();
		cliente.setSenha(bCryptPasswordEncoder.encode(newPass));
		clienteRepository.save(cliente);
		emailService.sendNewPasswordEmail(cliente, newPass); 
	}

	private String newPassword() {
		char[] vet = new char[10];
		for(int i=0; i< 10; i++) {
			vet[i]=randomChar();
		}
		
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if(opt == 0) { //gera um dígito ((0) 48 a (9) 57)
			return (char) (rand.nextInt(10) + 48); //numero aleatorio de 0 a 9 e somar isso com 48 que é o código 0
		} else if(opt == 1) { //gera letra maiuscula - codigo do A é 65
			return (char) (rand.nextInt(26) + 65);
		} else { //gera letra minuscula - código do a é 97
			return (char) (rand.nextInt(26) + 97);
		}
	}

}
