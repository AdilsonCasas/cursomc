package com.nelioalves.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class REST_Utils_URL {
	
	public static String metodoREST_utils_decodeParam(String var_str) throws UnsupportedEncodingException {
		try {
			return URLDecoder.decode(var_str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new UnsupportedEncodingException("ERRO_PADRAO#0029@UnsupportedEncodingException: "+e.getMessage());
		}
	}

	// este método quebra uma string de números separadas por vírgula em uma lista de inteiros, como a lista no final desta URL: 'http://localhost:8080/produtos/nome=computador&categorias=1,3,4'
	public static List<Integer> metodoREST_utils_decodeIntList(String var_str) {
		String[] var_vet = var_str.split(",");
		List<Integer> var_list = new ArrayList<>();
		for (int i=0; i<var_vet.length; i++) {
			var_list.add(Integer.parseInt(var_vet[i]));
		}
		return var_list;
		// toda a implementação acima pode ser substituída por uma única linha de código, assim:
		//return Arrays.asList(str.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
	}
	
	public static String metodoREST_utils_formataData_e_Hora_fromTimeStamp(Long var_dataTimeStamp) {
		// ex: "28/04/2018 11:14:02"
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); // usar "dd/MM/yyyy", não "dd/mm/yyyy" (MM = mês, mm = minuto)
	    Date dataAtual = new Date(var_dataTimeStamp);
	    return sd.format(dataAtual);        
	}

	public static String metodoREST_utils_formataHora_fromTimeStamp(Long var_dataTimeStamp) {
		return metodoREST_utils_formataData_e_Hora_fromTimeStamp(var_dataTimeStamp).substring(10);
	}

	public static String metodoREST_utils_formataData_fromTimeStamp(Long var_dataTimeStamp) {
	    String var_dataFormatada = metodoREST_utils_formataData_e_Hora_fromTimeStamp(var_dataTimeStamp); 
		return var_dataFormatada.substring(0, 11);
	}
}
