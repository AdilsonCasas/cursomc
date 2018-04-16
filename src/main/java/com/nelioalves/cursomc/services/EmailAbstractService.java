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

import com.nelioalves.cursomc.domain.PedidoEntity;

public abstract class EmailAbstractService implements EmailService {

	@Value("${default.sender}")
	private String var_sender;

	// este 'TemplateEngine' abaixo é do Thymeleaf
	@Autowired
	private TemplateEngine var_templateEngine;
	
	@Autowired
	private JavaMailSender var_javaMailSender;

// ===================================================== Processamento de email 'texto' ======================================================================
	@Override
	public void metodoService_sendOrderConfirmationEmail(PedidoEntity var_obj) {
		SimpleMailMessage var_sm = metodoService_prepareSimpleMailMessageFromPedido(var_obj);
		metodoService_sendEmail(var_sm);
	}

	protected SimpleMailMessage metodoService_prepareSimpleMailMessageFromPedido(PedidoEntity var_obj) {
		SimpleMailMessage var_sm = new SimpleMailMessage();
		var_sm.setTo(var_obj.getCliente().getEmail());
		var_sm.setFrom(var_sender);
		var_sm.setSubject("Pedido confirmado! Código: "+var_obj.getId());
		var_sm.setSentDate(new Date(System.currentTimeMillis()));
		var_sm.setText(var_obj.toString());
		return var_sm;
	}
	
// ===================================================== Processamento de email 'html' ======================================================================
	@Override
	public void metodoService_sendOrderConfirmationHtmlEmail(PedidoEntity var_obj) {
		try {
			MimeMessage var_mm = metodoService_prepareMimeMessageFromPedido(var_obj);
			metodoService_sendHtmlEmail(var_mm);
		}
		catch(MessagingException e) {
			metodoService_sendOrderConfirmationEmail(var_obj);
		}
	}

	protected MimeMessage metodoService_prepareMimeMessageFromPedido(PedidoEntity var_obj) throws MessagingException {
		MimeMessage var_mimeMessage = var_javaMailSender.createMimeMessage();
		MimeMessageHelper var_mmh = new MimeMessageHelper(var_mimeMessage,true);
		var_mmh.setTo(var_obj.getCliente().getEmail());
		var_mmh.setFrom(var_sender);
		var_mmh.setSubject("Pedido confirmado! Código: "+var_obj.getId());
		var_mmh.setSentDate(new Date(System.currentTimeMillis()));
		var_mmh.setText(metodoService_htmlFromTemplatePedido(var_obj),true);
		return var_mimeMessage;
	}

	protected String metodoService_htmlFromTemplatePedido(PedidoEntity var_obj) {
		// este 'Context' abaixo é do Thymeleaf
		Context var_context = new Context();
		// o 'setVariable' abaixo passa o objeto PedidoEntity para dentro do template 'confirmacaoPedido.html' e lhe dá o apelido de 'pedido', que é referenciado dentro do template html
		var_context.setVariable("pedido", var_obj);
		// o comando abaixo faz o Thymeleaf "processar" o contexto e retornar uma String (o texto html) já com os valores "populados" a partir do pedido.
		// por padrão o Thymeleaf processa os seus arquivos templates a partir da pasta "../src/main/resources/templates", por isso só foi colocado o path abaixo a partir de 'email' e o nome do arquivo "sem .html"
		return var_templateEngine.process("email/confirmacaoPedido", var_context);
	}

}
