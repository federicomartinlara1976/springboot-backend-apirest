package net.bounceme.chronos.springboot.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import net.bounceme.chronos.springboot.backend.apirest.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {

	Usuario findByUsername(String username);
	
	@Query("select u from Usuario u where u.username=?1")
	Usuario findByUsername2(String username);
}
