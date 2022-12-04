package net.bounceme.chronos.springboot.backend.apirest.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import net.bounceme.chronos.springboot.backend.apirest.models.entity.Cliente;
import net.bounceme.chronos.springboot.backend.apirest.models.entity.Region;
import net.bounceme.chronos.springboot.backend.apirest.models.services.IClienteService;
import net.bounceme.chronos.springboot.backend.apirest.models.services.IUploadFileService;
import net.bounceme.chronos.springboot.backend.apirest.utils.LogWrapper;

/**
 * @author federico
 *
 */
@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api")
@Slf4j
public class ClienteRestController {

	private static final String ERROR = "ERROR";
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IUploadFileService uploadService;
	
	/**
	 * @return
	 */
	@GetMapping("/clientes")
	public List<Cliente> index() {
		return clienteService.findAll();
	}
	
	/**
	 * @param page
	 * @return
	 */
	@GetMapping("/clientes/page/{page}")
	public Page<Cliente> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 4);
		return clienteService.findAll(pageable);
	}

	/**
	 * @param id
	 * @return
	 */
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();

		try {
			Cliente cliente = clienteService.findById(id);

			if (Objects.isNull(cliente)) {
				response.put("mensaje", String.format("El cliente con ID %d no existe", id));
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		} catch (DataAccessException e) {
			LogWrapper.error(log, ERROR, e);
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @param cliente
	 * @return
	 */
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/clientes")
	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {
		Map<String, Object> response = new HashMap<>();

		try {
			if (result.hasErrors()) {
				List<String> errors = result.getFieldErrors().stream()
						.map(error -> String.format("El campo '%s' %s", error.getField(), error.getDefaultMessage()))
						.collect(Collectors.toList());

				response.put("errors", errors);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}
			
			cliente = clienteService.save(cliente);

			response.put("mensaje", "El cliente ha sido creado con éxito");
			response.put("cliente", cliente);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			LogWrapper.error(log, ERROR, e);
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @param cliente
	 * @param id
	 * @return
	 */
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();

		try {
			if (result.hasErrors()) {
				List<String> errors = result.getFieldErrors().stream()
						.map(error -> String.format("El campo '%s' %s", error.getField(), error.getDefaultMessage()))
						.collect(Collectors.toList());

				response.put("errors", errors);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}
			
			Cliente clienteActual = clienteService.findById(id);
			if (Objects.isNull(clienteActual)) {
				response.put("mensaje", String.format("El cliente con ID %d no existe", id));
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}

			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setEmail(cliente.getEmail());
			clienteActual.setCreateAt(cliente.getCreateAt());
			clienteActual.setRegion(cliente.getRegion());
			clienteActual = clienteService.save(clienteActual);

			response.put("mensaje", "El cliente ha sido actualizado con éxito");
			response.put("cliente", clienteActual);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			LogWrapper.error(log, ERROR, e);
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @param id
	 */
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();

		try {
			Cliente cliente = clienteService.findById(id);
			
			String fotoAnterior = cliente.getFoto();
			uploadService.eliminar(fotoAnterior);
			
			clienteService.delete(id);

			response.put("mensaje", "El cliente ha sido borrado con éxito");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			LogWrapper.error(log, ERROR, e);
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * @param archivo
	 * @param id
	 * @return
	 */
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@PostMapping("/clientes/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			Cliente cliente = clienteService.findById(id);

			if (!archivo.isEmpty()) {
				String nombreArchivo = uploadService.copiar(archivo);
				
				String fotoAnterior = cliente.getFoto();
				uploadService.eliminar(fotoAnterior);
				
				cliente.setFoto(nombreArchivo);
				
				clienteService.save(cliente);
				
				response.put("cliente", cliente);
				response.put("mensaje", String.format("Has subido correctamente la imagen %s", nombreArchivo));
			}

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			LogWrapper.error(log, ERROR, e);
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
			LogWrapper.error(log, ERROR, e);
			response.put("error", e.getCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * @param nombreFoto
	 * @return
	 */
	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {
		
		Resource recurso = null;
		
		try {
			recurso = uploadService.cargar(nombreFoto);
		} catch (MalformedURLException e) {
			LogWrapper.error(log, ERROR, e);
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}
	
	/**
	 * @return
	 */
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/clientes/regiones")
	public List<Region> listarRegiones() {
		return clienteService.findAllRegiones();
	}
}
