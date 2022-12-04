package net.bounceme.chronos.springboot.backend.apirest.models.services;

import net.bounceme.chronos.springboot.backend.apirest.models.entity.Usuario;

public interface IUsuarioService {

	Usuario findByUsername(String username);
}
