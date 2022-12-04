package net.bounceme.chronos.springboot.backend.apirest.models.services;

import java.util.List;

import net.bounceme.chronos.springboot.backend.apirest.models.documents.Mensaje;

public interface ChatService {

	public List<Mensaje> obtenerUltimos10Mensajes();
	public Mensaje guardar(Mensaje mensaje);
}
