package net.bounceme.chronos.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Table(name="facturas")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Factura implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4248857504626027721L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@EqualsAndHashCode.Exclude
	private String descripcion;
	
	@Column
	@EqualsAndHashCode.Exclude
	private String observacion;
	
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	@EqualsAndHashCode.Exclude
	private Date createAt;
	
	@JsonIgnoreProperties(value={"facturas", "hibernateLazyInitializer", "handler"}, allowSetters = true)
	@ManyToOne(fetch=FetchType.LAZY)
	private Cliente cliente;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "factura_id")
	private List<ItemFactura> items;
	
	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}
	
	public Double getTotal() {
		Double total = 0.0;
		for (ItemFactura item : items) {
			total += item.getImporte();
		}
		return total;
	}
}
