package com.vivs.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.vivs.cursomc.domain.Pedido;

public interface EmailService {
	void sendOrderConfirmationEmail(Pedido obj);
	void sendEmail(SimpleMailMessage msg);
}
