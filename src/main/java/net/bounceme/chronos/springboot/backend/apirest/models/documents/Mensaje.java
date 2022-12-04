package net.bounceme.chronos.springboot.backend.apirest.models.documents;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="mensajes")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Mensaje implements Serializable {
	
	@Id
	private String id;

	private String texto;
	private Long fecha;
	private String username;
	private String tipo;
	private String color;

	private static final long serialVersionUID = -3777582564067492550L;

}
