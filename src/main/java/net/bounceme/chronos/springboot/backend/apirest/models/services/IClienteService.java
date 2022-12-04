package net.bounceme.chronos.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.bounceme.chronos.springboot.backend.apirest.models.entity.Cliente;
import net.bounceme.chronos.springboot.backend.apirest.models.entity.Factura;
import net.bounceme.chronos.springboot.backend.apirest.models.entity.Producto;
import net.bounceme.chronos.springboot.backend.apirest.models.entity.Region;

/**
 * @author federico
 *
 */
public interface IClienteService {
	/**
	 * @return
	 */
	List<Cliente> findAll();
	
	/**
	 * @param pageable
	 * @return
	 */
	Page<Cliente> findAll(Pageable pageable);
	
	/**
	 * @param cliente
	 * @return
	 */
	Cliente save(Cliente cliente);
	
	/**
	 * @param id
	 */
	void delete(Long id);
	
	/**
	 * @param id
	 * @return
	 */
	Cliente findById(Long id);
	
	/**
	 * @return
	 */
	List<Region> findAllRegiones();
	
	/**
	 * @param id
	 * @return
	 */
	Factura findFacturaById(Long id);
	
	/**
	 * @param factura
	 */
	Factura saveFactura(Factura factura);
	
	/**
	 * @param id
	 */
	void deleteFacturaById(Long id);
	
	/**
	 * @param termino
	 * @return
	 */
	List<Producto> findProductoByNombre(String termino);
}
