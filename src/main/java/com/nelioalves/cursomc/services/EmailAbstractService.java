package com.nelioalves.cursomc.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.nelioalves.cursomc.domain.PedidoDomain;

public abstract class EmailAbstractService implements EmailService {

	@Value("${default.sender}")
	private String sender;

	// este 'TemplateEngine' abaixo é do Thymeleaf
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;

// ===================================================== Processamento de email 'texto' ======================================================================
	@Override
	public void sendOrderConfirmationEmail(PedidoDomain obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(PedidoDomain obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido confirmado! Código: "+obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}
	
// ===================================================== Processamento de email 'html' ======================================================================
	@Override
	public void sendOrderConfirmationHtmlEmail(PedidoDomain obj) {
		try {
			MimeMessage mm = prepareMimeMessageFromPedido(obj);
			sendHtmlEmail(mm);
		}
		catch(MessagingException e) {
			sendOrderConfirmationEmail(obj);
		}
	}

	protected MimeMessage prepareMimeMessageFromPedido(PedidoDomain obj) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage,true);
		mmh.setTo(obj.getCliente().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Pedido confirmado! Código: "+obj.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplatePedido(obj),true);
		return mimeMessage;
	}

	protected String htmlFromTemplatePedido(PedidoDomain obj) {
		// este 'Context' abaixo é do Thymeleaf
		Context context = new Context();
		// o 'setVariable' abaixo passa o objeto PedidoDomain para dentro do template 'confirmacaoPedido.html' e lhe dá o apelido de 'pedido', que é referenciado dentro do template html
		context.setVariable("pedido", obj);
		// o comando abaixo faz o Thymeleaf "processar" o contexto e retornar uma String (o texto html) já com os valores "populados" a partir do pedido.
		// por padrão o Thymeleaf processa os seus arquivos templates a partir da pasta "../src/main/resources/templates", por isso só foi colocado o path abaixo a partir de 'email' e o nome do arquivo "sem .html"
		return templateEngine.process("email/confirmacaoPedido", context);
	}

}
