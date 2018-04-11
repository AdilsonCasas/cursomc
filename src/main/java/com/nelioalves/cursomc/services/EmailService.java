package com.nelioalves.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.nelioalves.cursomc.domain.PedidoDomain;

public interface EmailService {
	
	void metodoService_sendOrderConfirmationEmail(PedidoDomain var_obj);
	
	void metodoService_sendEmail(SimpleMailMessage var_msg);
	
	void metodoService_sendOrderConfirmationHtmlEmail(PedidoDomain var_obj);
	
	void metodoService_sendHtmlEmail(MimeMessage var_msg);

}
