package br.com.erudio.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.erudio.config.FileStorageConfig;
import br.com.erudio.exceptions.FileStorageException;
import br.com.erudio.exceptions.MyFileNotFoundException;

@Service
public class FileStorageService {
	
	private final Path fileStorageLocation; //caminho completo até aonde as pastas dos arquivos que serão salvos

	@Autowired
	public FileStorageService(FileStorageConfig fileStorageConfig) {
		Path path = Paths.get(fileStorageConfig.getUploadDir())
				.toAbsolutePath().normalize(); // inicializa a variavel da string que definimos no application Yaml e no uploadconfigs "UploadDir", normalizamos ela e transformamos ela em um Path do Java NIO
		
		this.fileStorageLocation = path;
		
		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception e) {
			throw new FileStorageException(
					"Could not create the directory where the uploaded files will be stored!", e);
		} //se o diretorio existe ele reusa senão ele cria
	}
	
	public String storeFile(MultipartFile file) {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			//Filename..txt
			if(filename.contains("..")) {
				throw new FileStorageException(
						"Sorry! Filename contains invalid path sequence " + filename); //validação da extensão se é valida (validando apenas .. nesse momento) e cria o arquivo vazio em disco (Mesma coisa de criar um txt com botão direito do mouse)
			}
			Path targetLocation = this.fileStorageLocation.resolve(filename);
			Files.copy(file.getInputStream(),//obtemos um array de string (Array de bytes do multipartfile), pequenos pedaços do arquivo.
					targetLocation, //caminho completo aonde vai gravar em disco 
					StandardCopyOption.REPLACE_EXISTING); //subistitua caso já possua algum com o mesmo nome.
			return filename; //caso seja necessário salvar na nuvem (servidor) alterar os caminhos acima.
		} catch (Exception e) {
			throw new FileStorageException(
					"Could not store file " + filename + ". Please try again!", e);
		}
	}
	
	public Resource loadFileAsResource(String filename) {
		try {
			Path filePath = this.fileStorageLocation.resolve(filename).normalize();
			Resource resource = new UrlResource(filePath.toUri()); //recupera caminho do local aonde esta os arquivos solicitado
			if( resource.exists()) return resource; //se o caminho tiver o conteudo ele retorna
			
			else {
				throw new MyFileNotFoundException("File not found" + filename); //Senao tiver conteudo retorna que não encontrou o arquivo
			}
		} catch (Exception e) {
			throw new MyFileNotFoundException("File not found" + filename,e);
		}
	}

}
