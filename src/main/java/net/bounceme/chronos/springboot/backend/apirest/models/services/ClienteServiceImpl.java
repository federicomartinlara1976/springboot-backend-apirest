package net.bounceme.chronos.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bounceme.chronos.springboot.backend.apirest.aspects.TrackTime;
import net.bounceme.chronos.springboot.backend.apirest.models.dao.IClienteDao;
import net.bounceme.chronos.springboot.backend.apirest.models.dao.IFacturaDao;
import net.bounceme.chronos.springboot.backend.apirest.models.dao.IProductoDao;
import net.bounceme.chronos.springboot.backend.apirest.models.entity.Cliente;
import net.bounceme.chronos.springboot.backend.apirest.models.entity.Factura;
import net.bounceme.chronos.springboot.backend.apirest.models.entity.Producto;
import net.bounceme.chronos.springboot.backend.apirest.models.entity.Region;

/**
 * @author federico
 *
 */
@Service
public class ClienteServiceImpl implements IClienteService {
	
	@Autowired
	private IClienteDao clienteDao;
	
	@Autowired
	private IFacturaDao facturaDao;
	
	@Autowired
	private IProductoDao productoDao;

	/**
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	@TrackTime
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}
	
	/**
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	@TrackTime
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteDao.findAll(pageable);
	}

	/**
	 *
	 */
	@Override
	@Transactional
	@TrackTime
	public Cliente save(Cliente cliente) {
		return clienteDao.save(cliente);
	}

	/**
	 *
	 */
	@Override
	@Transactional
	@TrackTime
	public void delete(Long id) {
		clienteDao.deleteById(id);
	}

	/**
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	@TrackTime
	public Cliente findById(Long id) {
		return clienteDao.findById(id).orElse(null);
	}
	
	/**
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	@TrackTime
	public List<Region> findAllRegiones() {
		return clienteDao.findAllRegiones();
	}

	@Override
	@Transactional(readOnly = true)
	@TrackTime
	public Factura findFacturaById(Long id) {
		return facturaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	@TrackTime
	public Factura saveFactura(Factura factura) {
		return facturaDao.save(factura);
	}

	@Override
	@Transactional
	@TrackTime
	public void deleteFacturaById(Long id) {
		facturaDao.deleteById(id);
	}

	@Override
	@Transactional
	@TrackTime
	public List<Producto> findProductoByNombre(String termino) {
		return productoDao.findByNombreContainingIgnoreCase(termino);
	}
}
