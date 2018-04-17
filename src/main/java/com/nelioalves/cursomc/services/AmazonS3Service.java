package com.nelioalves.cursomc.services;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class AmazonS3Service {

	private Logger var_LOG = LoggerFactory.getLogger(AmazonS3Service.class);
	
	@Autowired
	private AmazonS3 s3client;
	
	@Value("${s3.bucket}")
	private String par_BucketName;
	
	public void metodoService_uploadFile(String var_localFilePath) {
		try {
			var_LOG.info("Iniciando upload Amazon S3...");
			File file = new File(var_localFilePath);
			s3client.putObject(new PutObjectRequest(par_BucketName, "arq_teste.jpg", file));
			var_LOG.info("upload Amazon S3 finalizado OK...");
		}
		catch (AmazonServiceException e) {
			var_LOG.info("AmazonServiceException: "+e.getMessage());
			var_LOG.info("Status Code: "+e.getErrorCode());
		}
		catch (AmazonClientException e) {
			var_LOG.info("AmazonClientException: "+e.getMessage());
		}
	}
}
