package net.bounceme.chronos.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author federico
 *
 */
@Entity
@Table(name="productos")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Producto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4248857504626027721L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@EqualsAndHashCode.Exclude
	private String nombre;
	
	@Column
	@EqualsAndHashCode.Exclude
	private Double precio;
	
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	@EqualsAndHashCode.Exclude
	private Date createAt;
	
	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}
}
