package com.nelioalves.cursomc;

import java.io.FileReader;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	public static Integer IndiceMax_no_enumErroPadrao = 100;
	public static String desativarSeguranca = "NAO";

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Este método roda antes de qualquer outro método no sistema, pode ser usado para executar alguns procedimentos iniciais...
		
		if(!instantiateErrosPadraoDoSistema()) {
			throw new Exception("\n\n\nxiiiiiiiiiiiiiiiii marquinho...");
		}

	}

	public boolean instantiateErrosPadraoDoSistema() throws Exception {
		String var_strLinha;
		String var_NumErro;
		String var_auxStr;
		enumErroPadrao var_enumErroPadrao;
		Integer i = 0;
		FileReader var_fileReader = new FileReader("/home/lenovo/workspace/cursomc/MsgErroPadrao.txt");
		Scanner var_scanner = new Scanner(var_fileReader);
		var_scanner.useDelimiter("\\n");
        while (var_scanner.hasNext()&&(i<IndiceMax_no_enumErroPadrao)) {
        	var_strLinha = var_scanner.next();
        	if (var_strLinha.trim().indexOf('#') != 0) { // se a linha não começa com '#'

            	if (var_strLinha.startsWith("desativarSeguranca")) { // se a linha não começa com 'desativarSeguranca'
            		desativarSeguranca = var_strLinha.substring(var_strLinha.indexOf("=")+1);
            	}
            	else {
	        		// Numero do Erro Padrao
	                var_NumErro = var_strLinha.substring(0,var_strLinha.indexOf("@#$1"));
	                var_enumErroPadrao = enumErroPadrao.toEnum(Integer.parseInt(var_NumErro));
	
	        		// Mensagem Padrao do Erro
	                var_auxStr = var_strLinha.substring(var_strLinha.indexOf("@#$1")+4, var_strLinha.indexOf("@#$2"));
	                var_enumErroPadrao.setMsgPadraoParaErro(var_auxStr.trim());
	
	        		// Numero Complementar para o Erro
	                var_auxStr = var_strLinha.substring(var_strLinha.indexOf("@#$2")+4);
	                var_enumErroPadrao.setMsgComplementar(var_auxStr.trim());
	                i++;
            	}
        	}
        }
		var_scanner.close();
        var_fileReader.close();
        return true;
	}
}
