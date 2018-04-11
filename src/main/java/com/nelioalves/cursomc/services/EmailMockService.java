package com.nelioalves.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

//"Mock" é um email "de mentirinha", só vai aparecer no log do sistema
public class EmailMockService extends EmailAbstractService {

	// LOG criado como 'private static final' para que não seja necessário criar um LOG diferente (var local) a cada chamada, existirá somente um mesmo LOG 
	private static final Logger var_LOG = LoggerFactory.getLogger(EmailMockService.class);

	@Override
	public void metodoService_sendEmail(SimpleMailMessage var_msg) {
		var_LOG.info("Simulando envio de email...");
		var_LOG.info(var_msg.toString());
		var_LOG.info("email enviado.");
	}

	@Override
	public void metodoService_sendHtmlEmail(MimeMessage var_msg) {
		var_LOG.info("Simulando envio de email HTML...");
		var_LOG.info(var_msg.toString());
		var_LOG.info("email enviado.");
	}	
}
