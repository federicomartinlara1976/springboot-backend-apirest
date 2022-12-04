package net.bounceme.chronos.springboot.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@Table(name="facturas_items")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class ItemFactura implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4248857504626027721L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@EqualsAndHashCode.Exclude
	private Integer cantidad;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "producto_id")
	private Producto producto;
	
	/**
	 * @return
	 */
	public Double getImporte() {
		return cantidad.doubleValue() * producto.getPrecio();
	}
}
