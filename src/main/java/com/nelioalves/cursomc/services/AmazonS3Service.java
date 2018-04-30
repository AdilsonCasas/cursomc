package com.nelioalves.cursomc.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class AmazonS3Service {

	private Logger var_LOG = LoggerFactory.getLogger(AmazonS3Service.class);

	@Autowired
	private AmazonS3 s3client;

	@Value("${s3.bucket}")
	private String par_BucketName;

	public URI metodoService_uploadFile(MultipartFile var_multipartFile) throws Exception { // este tipo 'MultipartFile' é o tipo recebido pelo endpoint da internet
		try {
			String var_fileName = var_multipartFile.getOriginalFilename();
			InputStream var_inputStream = var_multipartFile.getInputStream();
			String var_contentType = var_multipartFile.getContentType(); // 'tipo' do arquivo, se é uma 'image' ou outro tipo...
			return metodoService_uploadFile(var_inputStream, var_fileName, var_contentType);
		} catch (IOException e) {
			throw new Exception("ERRO_PADRAO#0011@"+"IOException-"+e.getMessage());
		}
	}

	// esta é uma "sobrecarga" do método acima recebendo outros parâmetros
	public URI metodoService_uploadFile(InputStream var_inputStream, String var_fileName, String var_contentType) throws Exception {
		try {
			var_LOG.info("Iniciando upload Amazon S3...");
			ObjectMetadata var_meta = new ObjectMetadata();
			var_meta.setContentType(var_contentType);
			s3client.putObject(par_BucketName, var_fileName, var_inputStream, var_meta);
			return s3client.getUrl(par_BucketName, var_fileName).toURI();  // o metodo 's3client.getUrl(par_BucketName, var_fileName)' retorna uma URL, mas este método deve retornar um URI, por isso o 'toURI()' no final
		} catch (URISyntaxException e) {
			throw new Exception("ERRO_PADRAO#0012@"+"URISyntaxException-"+e.getMessage());
		}
	}
}
