package com.nelioalves.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

//"Mock" é um email "de mentirinha", só vai aparecer no log do sistema
public class EmailMockService extends EmailAbstractService{

	// LOG criado como 'private static final' para que não seja necessário criar um LOG diferente (var local) a cada chamada, existirá somente um mesmo LOG 
	private static final Logger LOG = LoggerFactory.getLogger(EmailMockService.class);

	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Simulando envio de email...");
		LOG.info(msg.toString());
		LOG.info("email enviado.");
	}
	
}
