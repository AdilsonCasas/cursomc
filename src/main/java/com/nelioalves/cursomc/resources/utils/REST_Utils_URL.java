package com.nelioalves.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class REST_Utils_URL {
	
	public static String metodoREST_utils_decodeParam(String var_str) {
		try {
			return URLDecoder.decode(var_str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
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
}
