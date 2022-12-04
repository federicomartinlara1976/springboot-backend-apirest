package net.bounceme.chronos.springboot.backend.apirest.models.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author federico
 *
 */
public interface IUploadFileService {
	
	/**
	 * @param nombreFoto
	 * @return
	 * @throws MalformedURLException
	 */
	Resource cargar(String nombreFoto) throws MalformedURLException;
	
	/**
	 * @param archivo
	 * @return
	 * @throws IOException
	 */
	String copiar(MultipartFile archivo) throws IOException;
	
	/**
	 * @param nombreFoto
	 * @return
	 */
	boolean eliminar(String nombreFoto);
	
	/**
	 * @param nombreFoto
	 * @return
	 */
	Path getPath(String nombreFoto);
}
