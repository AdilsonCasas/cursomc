package com.nelioalves.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class EmailSmtpService extends EmailAbstractService {

	@Autowired
	private MailSender mailSender;
	
	// LOG criado como 'private static final' para que não seja necessário criar um LOG diferente (var local) a cada chamada, existirá somente um mesmo LOG 
	private static final Logger LOG = LoggerFactory.getLogger(EmailSmtpService.class);

	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Enviando de email...");
		mailSender.send(msg);
		LOG.info("email enviado.");
	}

}
