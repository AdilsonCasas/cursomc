package com.nelioalves.cursomc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	// no heroku.com o curso se chama "curso-spring-ionic-adilson"
	// para logar-se no heroku execute no terminal (dentro da pasta do git do projeto): 'heroku login' (ele vai solicitar email+senha de login)
	// dentro da pasta do git para o projeto execute: 'heroku git:remote -a curso-spring-ionic-adilson'
	// o comando 'heroku config | grep CLEARDB_DATABASE_URL' fornece os dados remotos do bd mysql
	//   retornará algo assim: 'mysql://b5a5c5728a3d60:6b723d9e@us-cdbr-iron-east-05.cleardb.net/heroku_5e5e15448243a6c?reconnect=true'
	//   que indica: 'mysql://usuário:senha@host/url_do_servidor
	//   para usar no comando pra exportar a base de dados para dentro do heroku: '/opt/lampp/bin/mysql --host=us-cdbr-iron-east-05.cleardb.net --user=b5a5c5728a3d60 --password=6b723d9e --reconnect heroku_5e5e15448243a6c < /home/lenovo/workspace/heroku/curso_spring.sql'
	// meu link GitHub do curso no Heroku: 'https://git.heroku.com/curso-spring-ionic-adilson.git'
	// link da minha aplicação no Heroku: 'https://curso-spring-ionic-adilson.herokuapp.com/'
	// no github.com minha área é 'AdilsonCasas'
	// Para fazer um 'push' do projeto no sistema é necessário estar logado no heroku e executar 'git push heroku master'

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Este método roda antes de qualquer outro método no sistema, pode ser usado para executar alguns procedimentos iniciais...
	}
}
