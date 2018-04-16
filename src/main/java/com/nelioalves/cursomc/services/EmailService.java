package com.nelioalves.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.nelioalves.cursomc.domain.PedidoEntity;

public interface EmailService {
	
	void metodoService_sendOrderConfirmationEmail(PedidoEntity var_obj);
	
	void metodoService_sendEmail(SimpleMailMessage var_msg);
	
	void metodoService_sendOrderConfirmationHtmlEmail(PedidoEntity var_obj);
	
	void metodoService_sendHtmlEmail(MimeMessage var_msg);

}
