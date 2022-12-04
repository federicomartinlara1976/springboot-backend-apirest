package net.bounceme.chronos.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.bounceme.chronos.springboot.backend.apirest.models.entity.Cliente;
import net.bounceme.chronos.springboot.backend.apirest.models.entity.Region;

/**
 * @author federico
 *
 */
public interface IClienteDao extends JpaRepository<Cliente, Long> {
	
	/**
	 * @return
	 */
	@Query("from Region")
	List<Region> findAllRegiones();
}
