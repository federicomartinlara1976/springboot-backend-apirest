package net.bounceme.chronos.springboot.backend.apirest.models.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import net.bounceme.chronos.springboot.backend.apirest.aspects.TrackTime;
import net.bounceme.chronos.springboot.backend.apirest.utils.LogWrapper;

/**
 * @author federico
 *
 */
@Service
@Slf4j
public class UploadFileServiceImpl implements IUploadFileService {
	
	private static final String UPLOADS = "uploads";
	private static final String ERROR = "ERROR";

	/**
	 *
	 */
	@Override
	@TrackTime
	public Resource cargar(String nombreFoto) throws MalformedURLException {
		Path rutaArchivo = getPath(nombreFoto);
		Resource recurso = new UrlResource(rutaArchivo.toUri()); 
		
		if (!Objects.isNull(recurso) && !recurso.exists() && !recurso.isReadable()) {
			rutaArchivo = Paths.get("src/main/resources/static/images").resolve("not_user.png").toAbsolutePath();
			try {
				recurso = new UrlResource(rutaArchivo.toUri());
			} catch (MalformedURLException e) {
				LogWrapper.error(log, ERROR, e);
			} 
			LogWrapper.error(log, "No se pudo cargar la imagen %s", nombreFoto);
		}
		
		return recurso;
	}

	/**
	 *
	 */
	@Override
	@TrackTime
	public String copiar(MultipartFile archivo) throws IOException {
		String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
		Path rutaArchivo = getPath(nombreArchivo);
		LogWrapper.debug(log, "Ruta del archivo: %s", rutaArchivo);
		
		Files.copy(archivo.getInputStream(), rutaArchivo);
		
		return nombreArchivo;
	}

	/**
	 *
	 */
	@Override
	@TrackTime
	public boolean eliminar(String nombreFoto) {
		
		if (StringUtils.isNotBlank(nombreFoto)) {
			Path rutaFotoAnterior = getPath(nombreFoto);
			File archivoAnterior = rutaFotoAnterior.toFile();
			
			if (archivoAnterior.exists() && archivoAnterior.canRead()) {
				archivoAnterior.delete();
				return true;
			}
		}
		
		return false;
	}

	/**
	 *
	 */
	@Override
	public Path getPath(String nombreFoto) {
		return Paths.get(UPLOADS).resolve(nombreFoto).toAbsolutePath();
	}

}
