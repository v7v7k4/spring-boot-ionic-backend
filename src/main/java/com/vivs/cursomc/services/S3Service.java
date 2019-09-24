package com.vivs.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class S3Service {

	private Logger LOG = LoggerFactory.getLogger(S3Service.class);

//	@Autowired
//	private AmazonS3 s3client;

	@Value("${s3.region}")
	private String bucketName;

//	public URI upload(MultipartFile multipartfile) {
//		
//		try {
//			String fileName = multipartfile.getOriginalFilename();
//			InputStream is = multipartfile.getInputStream();
//			String contentType = multipartfile.getContentType();
//			return upload(is, fileName, contentType);
//		} catch (IOException e) {
//			throw new FileException("Erro de IO: " + e.getMessage());
//		}
//
//	}

//	public URI upload(InputStream is, String filename, String contentType) {
//		ObjectMetadata meta = new ObjectMetadata();
//		meta.setContentType(contentType);
//		LOG.info("Iniciando upload");
//		s3client.putObject(bucketName, filename, is, meta);
//		LOG.info("Upload finalizado");
//		try {
//			return s3client.getUrl(bucketName, filename).toURI();
//		} catch (URISyntaxException e) {
//			throw new FileException("Erro ao converter URL para URI");
//		}
//	}

}
