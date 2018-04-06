package com.nelioalves.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class REST_utilsURL {
	
	public static String REST_utils_decodeParam(String str) {
		try {
			return URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	// este método quebra uma string de números separadas por vírgula em uma lista de inteiros, como a lista no final desta URL: 'http://localhost:8080/produtos/nome=computador&categorias=1,3,4'
	public static List<Integer> REST_utils_decodeIntList(String str) {
		String[] vet = str.split(",");
		List<Integer> list = new ArrayList<>();
		for (int i=0; i<vet.length; i++) {
			list.add(Integer.parseInt(vet[i]));
		}
		return list;
		// toda a implementação acima pode ser substituída por uma única linha de código, assim:
		//return Arrays.asList(str.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
	}
}
