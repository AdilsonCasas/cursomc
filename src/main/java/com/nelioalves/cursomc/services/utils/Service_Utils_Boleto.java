package com.nelioalves.cursomc.services.utils;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.PagamentoComBoletoDomain;

@Service
public class Service_Utils_Boleto {
	
	public void PreencherPagtoComBoleto(PagamentoComBoletoDomain pgto, Date InstanteDoPedido) {
		Calendar calend = Calendar.getInstance();
		calend.setTime(InstanteDoPedido);
		// acrescenta 7 dias à data 'InstanteDoPedido' --> este '7 dias a mais' para o vcto foi aleatório, deve-se definir a correta regra de negócio para isto.
		// numa situação real de um sistema comercial deve-se chamar um '"web Service' que retorna um boleto
		calend.add(Calendar.DAY_OF_MONTH, 7);
		pgto.setDataPagamento(calend.getTime());
	}
}
