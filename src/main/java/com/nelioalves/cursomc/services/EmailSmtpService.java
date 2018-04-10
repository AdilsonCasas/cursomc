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
	private MailSender mailSender;
	
	@Autowired
	private JavaMailSender javaMailSender;

	// LOG criado como 'private static final' para que não seja necessário criar um LOG diferente (var local) a cada chamada, existirá somente um mesmo LOG 
	private static final Logger LOG = LoggerFactory.getLogger(EmailSmtpService.class);

	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Enviando de email...");
		mailSender.send(msg);
		LOG.info("email enviado.");
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("Enviando de email...");
		javaMailSender.send(msg);
		LOG.info("email enviado.");
	}

}
