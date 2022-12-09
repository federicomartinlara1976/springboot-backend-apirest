package net.bounceme.chronos.springboot.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5272580936599428032L;

	@Id
	private Long id;
	
	private String nombre;
}
