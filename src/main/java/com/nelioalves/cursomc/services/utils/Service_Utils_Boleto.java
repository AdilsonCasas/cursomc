package com.nelioalves.cursomc.services.utils;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.PagamentoComBoletoEntity;

@Service
public class Service_Utils_Boleto {
	
	public void metodoService_utils_PreencherPagtoComBoleto(PagamentoComBoletoEntity var_pgto, Date var_InstanteDoPedido) {
		Calendar var_calend = Calendar.getInstance();
		var_calend.setTime(var_InstanteDoPedido);
		// acrescenta 7 dias à data 'InstanteDoPedido' --> este '7 dias a mais' para o vcto foi aleatório, deve-se definir a correta regra de negócio para isto.
		// numa situação real de um sistema comercial deve-se chamar um '"web Service' que retorna um boleto
		var_calend.add(Calendar.DAY_OF_MONTH, 7);
		var_pgto.setDataPagamento(var_calend.getTime());
	}
}
