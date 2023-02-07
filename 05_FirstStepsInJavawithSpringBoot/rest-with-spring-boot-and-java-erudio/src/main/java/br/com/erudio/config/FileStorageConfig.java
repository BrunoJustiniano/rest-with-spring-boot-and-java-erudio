package br.com.erudio.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "file")
public class FileStorageConfig {

		private String uploadDir; //é o mesmo nome definido no ApplicationYaml, porém seguindo as regras do Java

		public String getUploadDir() {
			return uploadDir;
		}

		public void setUploadDir(String uploadDir) {
			this.uploadDir = uploadDir;
		}
}
