package com.vivs.cursomc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.vivs.cursomc.services.DBService;

@Configuration
@Profile("dev") //application-dev.properties
/*todos os beans que estiverem dentro dessa classe serão ativados apenas quando o profile de
 * dev estiver ativo no application.properties
 */
public class DevConfig {
	
	@Autowired
	private DBService dbService;
	
	/*
	 * Só chama o método dbService.instantiateTestDatabase() se 
	 * for (spring.jpa.hibernate.ddl-auto=create) para não duplicar os dados
	 */
	@Value("${spring.jpa.hibernate.ddl-auto}") //armazena o valor dessa chave na variável abaixo strategy
	private String strategy;
	
	@Bean
	public boolean instantiateDatabase() {
		if(!"create".equals(strategy)) {
			return false;
		}
		dbService.instantiateTestDatabase();
		return true;
	}

}
