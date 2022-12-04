package net.bounceme.chronos.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import net.bounceme.chronos.springboot.backend.apirest.models.entity.Factura;

public interface IFacturaDao extends CrudRepository<Factura, Long> {

}
