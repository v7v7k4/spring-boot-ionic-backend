package com.vivs.cursomc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.vivs.cursomc.services.DBService;

@Configuration
@Profile("test") //application-test.properties
/*todos os beans que estiverem dentro dessa classe serão ativados apenas quando o profile de
 * test estiver ativo no application.properties
 */
public class TestConfig {
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantiateDatabase() {
		dbService.instantiateTestDatabase();
		return true;
	}

}
