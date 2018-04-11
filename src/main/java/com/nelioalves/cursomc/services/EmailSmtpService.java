package com.nelioalves.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailSmtpService extends EmailAbstractService {

	@Autowired
	private MailSender var_mailSender;
	
	@Autowired
	private JavaMailSender var_javaMailSender;

	// LOG criado como 'private static final' para que não seja necessário criar um LOG diferente (var local) a cada chamada, existirá somente um mesmo LOG 
	private static final Logger var_LOG = LoggerFactory.getLogger(EmailSmtpService.class);

	@Override
	public void metodoService_sendEmail(SimpleMailMessage var_msg) {
		var_LOG.info("Enviando de email...");
		var_mailSender.send(var_msg);
		var_LOG.info("email enviado.");
	}

	@Override
	public void metodoService_sendHtmlEmail(MimeMessage var_msg) {
		var_LOG.info("Enviando de email...");
		var_javaMailSender.send(var_msg);
		var_LOG.info("email enviado.");
	}

}
