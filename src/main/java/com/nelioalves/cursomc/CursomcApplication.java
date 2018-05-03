package com.nelioalves.cursomc;

import java.io.FileReader;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
// pesquise isso: https://spring.io/guides/gs/spring-boot-docker/
//		e isso: "docker spring boot example" na pesquisa do googgle
	
	
/*
 *
 * Sobre o arquivo "properties", padrão do Springframework:		
 *     descrição das variáveis no arq 'application.properties':
 *           default.sender=pp890645@gmail.com --> email usado para enviar os emails do sistema
 *          default.recipient=adilson.casas@gmail.com
 *     		jwt.segredo=SequenciaDeCaracteresParaAssinarTokenQuantoMaiorEMaisEstranhaEstaPalavraMelhororaASeguranca
 *     		jwt.tempoexpira=86400000 --> define o tempo de expiração do token JWT, sendo 60.000 = 1 minuto
 *           aws.access_key_id= ---> Amazon S3
 *           aws.secret_access_key=  ---> Amazon S3
 *     		s3.bucket=curso-spring-ionic  ---> Amazon S3
 *     		s3.region=sa-east-1  ---> Amazon S3
 *         obs: no link https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/using-regions-availability-zones.html#concepts-regions-availability-zones
 *              vc pega o nome/descrição da região a ser colocada na var 's3.region'
 *
 * Sobre o serviço 'S3' da Amazon:
 *      O serviço 'S3' da Amazon é um 'storage' para arquivos (em geral imagens do nosso sistema), estes arquivos são armazenados
 *      em uma unidade lógica chamada 'Bucket', o nome do meu Bucket criado  no S3 é: 'curso-udemy-spring-ionic', user 'curso-udemy-spring-ionic-user'.
 *
 *      Tornando o bucket com acesso público para leitura --> copie o script abaixo para aba "Permissions" no Bucket,  depois em BucketPolicy, dentro do site da Amazon/S3
 *     {
 *     	"Version": "2008-10-17",
 *     	"Statement": [
 *     		{
 *     			"Sid": "AllowPublicRead",
 *     			"Effect": "Allow",
 *     			"Principal": {
 *     				"AWS": "*"
 *     			},
 *     			"Action": [
 *     				"s3:GetObject"
 *     			],
 *     			"Resource": [
 *     				"arn:aws:s3:::curso-udemy-spring-ionic/*"
 *     			]
 *     		}
 *     	]
 *     }
 *
 *
 * Sobre o bd 'h2':
 *		O bd h2 é um banco de dados em memória, para testes, para pequenas porções de dados.
 *      Para acessar o bd "h2" em teste use: http://localhost:8080/h2-console/login.jsp?jsessionid=ee88b77a5a1a8ccd8e0ff77be97186e0
 *
 *
 * Sobre Heroku:
 *     no heroku.com o curso se chama "curso-spring-ionic-adilson", o arquivo "Procfile" é o arq de config do heroku no spring 
 *     para logar-se no heroku execute no terminal (dentro da pasta do git do projeto): 'heroku login' (ele vai solicitar email+senha de login)
 *     dentro da pasta do git para o projeto execute: 'heroku git:remote -a curso-spring-ionic-adilson'
 *     o comando 'heroku config | grep CLEARDB_DATABASE_URL' fornece os dados remotos do bd mysql
 *       retornará algo assim: 'mysql://b5a5c5728a3d60:6b723d9e@us-cdbr-iron-east-05.cleardb.net/heroku_5e5e15448243a6c?reconnect=true'
 *       que indica: 'mysql://usuário:senha@host/url_do_servidor
 *       para usar no comando pra exportar a base de dados para dentro do heroku: '/opt/lampp/bin/mysql --host=us-cdbr-iron-east-05.cleardb.net --user=b5a5c5728a3d60 --password=6b723d9e --reconnect heroku_5e5e15448243a6c < /home/lenovo/workspace/heroku/curso_spring.sql'
 *     meu link GitHub do curso no Heroku: 'https://git.heroku.com/curso-spring-ionic-adilson.git'
 *     link da minha aplicação no Heroku: 'https://curso-spring-ionic-adilson.herokuapp.com/'
 *
 *
 * Sobre GitHub:
 *     no github.com minha área/login é 'AdilsonCasas'
 *
 *
 * Sobre Spring Boot Framework:
 *     a anotação '@Bean' torna a classe ou o método um 'Componente' dentro do framework, e que portanto pode ser usado em outra parte qualquer da aplicação
 *
 *
 * Sobre "infinity scroll":
 *     é aquela tela nos sites que carregam parte dos dados até prencher a tela, e quando o usuário rola a tela (scroll) até o final da tela mais
 *     dados são carregados e a tela aumenta (para baixo) para conter os novos dados.
 *     
 *     
 * Exemplos do uso do Try/Catch:
 *      try {
 *          Thread.sleep(1000);
 *          System.out.println(url);
 *          return getUrlContentPage(url);
 *      } catch (ParseException | IOException | InterruptedException e) {
 *          return null;
 *      } finally {
 *                  
 *      }
 *
 *		try {
 *			var_CredenciaisDTO = new ObjectMapper().readValue(var_req.getInputStream(), DTO_Credenciais.class);
 *		} catch (JsonParseException e) {
 *			// TODO Auto-generated catch block
 *			e.printStackTrace();
 *		} catch (JsonMappingException e) {
 *			// TODO Auto-generated catch block
 *			e.printStackTrace();
 *		} catch (IOException e) {
 *			// TODO Auto-generated catch block
 *			e.printStackTrace();
 *		}
 *     
 *     
 *
 *
*/


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
