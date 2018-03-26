package com.anschau.udemy.services;

import org.springframework.mail.SimpleMailMessage;

import com.anschau.udemy.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
