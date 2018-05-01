package com.nelioalves.cursomc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AmazonS3Config {

	@Value("${aws.access_key_id}")
	private String par_awsId;
	
	@Value("${aws.secret_access_key}")
	private String par_awsKey;
	
	@Value("${s3.region}")
	private String par_region;

	@Bean
	public AmazonS3 s3client() {
		BasicAWSCredentials var_awsCred = new BasicAWSCredentials(par_awsId, par_awsKey);  
		AmazonS3 s3client = AmazonS3ClientBuilder.standard()
							.withRegion(Regions.fromName(par_region))
				            .withCredentials(new AWSStaticCredentialsProvider(var_awsCred)).build();
		return s3client;
	}
	
}
