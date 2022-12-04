package net.bounceme.chronos.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import net.bounceme.chronos.springboot.backend.apirest.models.entity.Producto;

/**
 * @author federico
 *
 */
public interface IProductoDao extends CrudRepository<Producto, Long> {

	/**
	 * @param termino
	 * @return
	 */
	@Query("select p from Producto p where p.nombre like %?1%")
	public List<Producto> findByNombre(String termino);
	
	/**
	 * @param termino
	 * @return
	 */
	public List<Producto> findByNombreContainingIgnoreCase(String termino);
	
	/**
	 * @param termino
	 * @return
	 */
	public List<Producto> findByNombreStartingWithIgnoreCase(String termino);
}
