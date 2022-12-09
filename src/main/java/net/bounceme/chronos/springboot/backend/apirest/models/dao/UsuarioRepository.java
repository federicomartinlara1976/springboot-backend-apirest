package net.bounceme.chronos.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import net.bounceme.chronos.springboot.backend.apirest.models.entity.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

	@Query("{ 'email' : ?0 }")
	List<Usuario> findByEmail(String email);
}
