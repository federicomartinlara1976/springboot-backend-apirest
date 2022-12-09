package net.bounceme.chronos.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="usuarios")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6463614089762258138L;
	
	@Id
	private String id;
	
	private String password;
	
	private String nombre;
	
	private String apellido;
	
	private String email;
	
	private List<Role> roles;
}
