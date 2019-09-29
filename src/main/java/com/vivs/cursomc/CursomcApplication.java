package com.vivs.cursomc;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.util.unit.DataSize;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

//	@Autowired
//	private S3Service s3service;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	// executar a instanciação no momento que a aplicação iniciar
	@Override
	public void run(String... args) throws Exception {
//		s3service.upload("C:\\teste\\IMG-20161225-WA0029.jpg");
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		// messageSource.setUseCodeAsDefaultMessage(true);
		return messageSource;
	}

//	@Bean
//	public LocalValidatorFactoryBean getValidator() {
//		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
//		bean.setValidationMessageSource(messageSource());
//		return bean;
//	}

	@Bean
	MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize(DataSize.ofBytes(512000000L));
		factory.setMaxRequestSize(DataSize.ofBytes(512000000L));
		return factory.createMultipartConfig();
	}

}
