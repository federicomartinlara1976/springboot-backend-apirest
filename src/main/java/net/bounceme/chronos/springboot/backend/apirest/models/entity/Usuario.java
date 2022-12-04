package net.bounceme.chronos.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6463614089762258138L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true, length=20)
	private String username;
	
	@Column
	private String password;
	
	@Column
	private Boolean enabled;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="usuarios_roles", joinColumns = @JoinColumn(name="usuario_id"), 
		inverseJoinColumns = @JoinColumn(name="roles_id"),
		uniqueConstraints = {@UniqueConstraint(columnNames={"usuario_id", "roles_id"})})
	private List<Role> roles;
	
	@Column
	private String nombre;
	
	@Column
	private String apellido;
	
	@Column(unique=true)
	private String email;
}
