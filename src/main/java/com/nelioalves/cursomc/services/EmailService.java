package com.nelioalves.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.nelioalves.cursomc.domain.PedidoDomain;

public interface EmailService {
	
	void sendOrderConfirmationEmail(PedidoDomain obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(PedidoDomain obj);
	
	void sendHtmlEmail(MimeMessage msg);

}
