package net.bounceme.chronos.springboot.backend.apirest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import net.bounceme.chronos.springboot.backend.apirest.models.dao.IClienteDao;
import net.bounceme.chronos.springboot.backend.apirest.models.entity.Cliente;
import net.bounceme.chronos.springboot.backend.apirest.models.services.ClienteServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

	@Mock
	IClienteDao clienteDao;

	@InjectMocks
	ClienteServiceImpl clienteServiceImpl;

	private List<Cliente> clientes;
	
	private Cliente cliente;
	
	private Page<Cliente> page;
	
	private Optional<Cliente> oCliente;

	@BeforeEach
	public void init() {
		clientes = new ArrayList<>();

		cliente = Cliente.builder().id(1L).nombre("Federico").apellido("Martin").email("federico@bolsaideas.com").createAt(new Date()).build();
	
		clientes.add(cliente);
		
		page = new PageImpl<>(clientes);
		
		oCliente = Optional.of(cliente);
	}

	@Test
	public void testFindAll() {
		when(clienteDao.findAll()).thenReturn(clientes);
		assertEquals(clientes, clienteServiceImpl.findAll());
	}
	
	@Test
	public void testFindAllPageable() {
		Pageable pageable = PageRequest.of(1, 1);
		
		when(clienteDao.findAll(pageable)).thenReturn(page);
		assertEquals(page, clienteServiceImpl.findAll(pageable));
	}
	
	@Test
	public void testFindById() {
		Long id = 1L;
		
		when(clienteDao.findById(id)).thenReturn(oCliente);
		assertEquals(oCliente.get(), clienteServiceImpl.findById(id));
	}
	
	@Test
	public void testSave() {
		when(clienteDao.save(cliente)).thenReturn(cliente);
		assertEquals(oCliente.get(), clienteServiceImpl.save(cliente));
	}
	
	@Test
	public void testDelete() {
		Long id = 1L;
		
		doNothing().when(clienteDao).deleteById(id);
		clienteServiceImpl.delete(id);
	}
}
