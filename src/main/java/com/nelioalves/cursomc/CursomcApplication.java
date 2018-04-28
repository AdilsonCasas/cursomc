package com.nelioalves.cursomc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	
	
	
	
// pesquise isso: https://spring.io/guides/gs/spring-boot-docker/
//		e isso: "docker spring boot example" na pesquisa do googgle
	
	
	
	
		
	// descrição das variáveis no arq 'application.properties':
	//      default.sender=pp890645@gmail.com --> email usado para enviar os emails do sistema
	//      default.recipient=adilson.casas@gmail.com
	//		jwt.segredo=SequenciaDeCaracteresParaAssinarTokenQuantoMaiorEMaisEstranhaEstaPalavraMelhororaASeguranca
	//		jwt.tempoexpira=86400000 --> define o tempo de expiração do token JWT, sendo 60.000 = 1 minuto
	//      aws.access_key_id= ---> Amazon S3
	//      aws.secret_access_key=  ---> Amazon S3
	//		s3.bucket=curso-spring-ionic  ---> Amazon S3
	//		s3.region=sa-east-1  ---> Amazon S3
	//    obs: no link https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/using-regions-availability-zones.html#concepts-regions-availability-zones
	//         vc pega o nome/descrição da região a ser colocada na var 's3.region'
	//
	// O serviço 'S3' da Amazon é um 'storage' para arquivos (em geral imagens do nosso sistema), estes arquivos são armazenados
	// em uma unidade lógica chamada 'Bucket', o nome do meu Bucket criado  no S3 é: 'curso-udemy-spring-ionic', user 'curso-udemy-spring-ionic-user'
	// Tornando o bucket com acesso público para leitura --> copie o script abaixo para aba "Permissions" no Bucket,  depois em BucketPolicy, dentro do site da Amazon/S3
	//{
	//	"Version": "2008-10-17",
	//	"Statement": [
	//		{
	//			"Sid": "AllowPublicRead",
	//			"Effect": "Allow",
	//			"Principal": {
	//				"AWS": "*"
	//			},
	//			"Action": [
	//				"s3:GetObject"
	//			],
	//			"Resource": [
	//				"arn:aws:s3:::curso-udemy-spring-ionic/*"
	//			]
	//		}
	//	]
	//}
	//
	// Para acessar o bd "h2" em teste use: http://localhost:8080/h2-console/login.jsp?jsessionid=ee88b77a5a1a8ccd8e0ff77be97186e0
	// no heroku.com o curso se chama "curso-spring-ionic-adilson"
	//
	// para logar-se no heroku execute no terminal (dentro da pasta do git do projeto): 'heroku login' (ele vai solicitar email+senha de login)
	// dentro da pasta do git para o projeto execute: 'heroku git:remote -a curso-spring-ionic-adilson'
	// o comando 'heroku config | grep CLEARDB_DATABASE_URL' fornece os dados remotos do bd mysql
	//   retornará algo assim: 'mysql://b5a5c5728a3d60:6b723d9e@us-cdbr-iron-east-05.cleardb.net/heroku_5e5e15448243a6c?reconnect=true'
	//   que indica: 'mysql://usuário:senha@host/url_do_servidor
	//   para usar no comando pra exportar a base de dados para dentro do heroku: '/opt/lampp/bin/mysql --host=us-cdbr-iron-east-05.cleardb.net --user=b5a5c5728a3d60 --password=6b723d9e --reconnect heroku_5e5e15448243a6c < /home/lenovo/workspace/heroku/curso_spring.sql'
	// meu link GitHub do curso no Heroku: 'https://git.heroku.com/curso-spring-ionic-adilson.git'
	// link da minha aplicação no Heroku: 'https://curso-spring-ionic-adilson.herokuapp.com/'
	//
	// no github.com minha área é 'AdilsonCasas'
	// Para fazer um 'push' do projeto no sistema é necessário estar logado no heroku e executar 'git push heroku master'
	//
	// a anotação '@Bean' torna a classe ou o método um 'Componente' dentro do framework, e que portanto pode ser usado em outra parte qualquer da aplicação

	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Este método roda antes de qualquer outro método no sistema, pode ser usado para executar alguns procedimentos iniciais...
	}
}
