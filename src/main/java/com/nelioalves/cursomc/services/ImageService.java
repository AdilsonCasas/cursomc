package com.nelioalves.cursomc.services;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {
	
	// este método provê funcionalidades de Imagens
	
	// um arquivo 'BufferedImage' é um arquivo no formato '.JPG', este metodo transforma um arq. recebido como parâmetro do tipo 'MultipartFile' e o transforma em JPG
	public BufferedImage metodoService_getJpgImageFromFile(MultipartFile var_uploadedFile) throws Exception {
		String var_extensaoArq = FilenameUtils.getExtension(var_uploadedFile.getOriginalFilename());
		if(!"png".equals(var_extensaoArq) && !"jpg".equals(var_extensaoArq)) {
			throw new Exception("ERRO_PADRAO#0013@"+"...");
		}
		try {
			BufferedImage var_imgBufferedImage = ImageIO.read(var_uploadedFile.getInputStream());
			if(!"png".equals(var_extensaoArq)) {
				var_imgBufferedImage = metodoService_pngTojpg(var_imgBufferedImage);
			}
			return var_imgBufferedImage;
		} catch (IOException e) {
			throw new Exception("ERRO_PADRAO#0014@"+"IOException-...");
		}
	}

	public BufferedImage metodoService_pngTojpg(BufferedImage var_imgBufferedImage) {
		BufferedImage var_jpgImage = new BufferedImage(var_imgBufferedImage.getWidth(), var_imgBufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
		var_jpgImage.createGraphics().drawImage(var_jpgImage, 0, 0, Color.WHITE, null);
		return var_jpgImage;
	}
	
	public InputStream metodoService_getInputStream(BufferedImage var_image, String var_extensaoImage) throws Exception {
		try {
			ByteArrayOutputStream var_os = new ByteArrayOutputStream();
			ImageIO.write(var_image, var_extensaoImage, var_os);
			return new ByteArrayInputStream(var_os.toByteArray());
		} catch (IOException e) {
			throw new Exception("ERRO_PADRAO#0015@"+"IOException-,,,");
		}
	}

	public BufferedImage metodoService_cropSquare(BufferedImage var_imgSource) {
		int var_min = (var_imgSource.getHeight() <= var_imgSource.getWidth()) ? var_imgSource.getHeight() : var_imgSource.getWidth();
		return Scalr.crop(
				var_imgSource,
				(var_imgSource.getWidth()/2) - (var_min/2),
				(var_imgSource.getHeight()/2) - (var_min/2),
				var_min,
				var_min);
	}

	public BufferedImage metodoService_resize(BufferedImage var_imgSource, int var_size) {
		return Scalr.resize(var_imgSource, Scalr.Method.ULTRA_QUALITY, var_size);
	}
}
