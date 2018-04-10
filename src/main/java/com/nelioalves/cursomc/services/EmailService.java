package com.nelioalves.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.nelioalves.cursomc.domain.PedidoDomain;

public interface EmailService {
	
	void sendOrderConfirmationEmail(PedidoDomain obj);
	
	void sendEmail(SimpleMailMessage msg);

}
