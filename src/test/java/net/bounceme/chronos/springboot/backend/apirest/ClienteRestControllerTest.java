package net.bounceme.chronos.springboot.backend.apirest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import net.bounceme.chronos.springboot.backend.apirest.controllers.ClienteRestController;
import net.bounceme.chronos.springboot.backend.apirest.models.entity.Cliente;
import net.bounceme.chronos.springboot.backend.apirest.models.services.IClienteService;

@ExtendWith(MockitoExtension.class)
public class ClienteRestControllerTest {

	@Mock
	IClienteService clienteService;
	
	private BindingResult bindingResult;

	@InjectMocks
	ClienteRestController clienteRestController;

	private List<Cliente> clientes;
	
	private Cliente cliente;
	
	private Page<Cliente> page;
	
	private ResponseEntity<Cliente> responseEntity;

	@BeforeEach
	public void init() {
		clientes = new ArrayList<>();

		cliente = Cliente.builder().id(1L).nombre("Federico").apellido("Martin").email("federico@bolsaideas.com").createAt(new Date()).build();
	
		clientes.add(cliente);
		
		page = new PageImpl<>(clientes);
		
		responseEntity = new ResponseEntity<>(cliente, HttpStatus.OK);
		
		bindingResult = mock(BindingResult.class);
	}

	@Test
	public void testCreate() {
		when(clienteService.save(ArgumentMatchers.any(Cliente.class))).thenReturn(cliente);
		assertEquals(HttpStatus.CREATED, clienteRestController.create(cliente, bindingResult).getStatusCode());
	}
	
	@Test
	public void testDelete() {
		Long id = 1L;
		
		doNothing().when(clienteService).delete(ArgumentMatchers.anyLong());
		clienteRestController.delete(id);
	}
	
	@Test
	public void testIndex() {
		when(clienteService.findAll()).thenReturn(clientes);
		assertEquals(clientes, clienteRestController.index());
	}
	
	@Test
	public void testIndexPageable() {
		Pageable pageable = PageRequest.of(1, 4);
		
		when(clienteService.findAll(pageable)).thenReturn(page);
		assertEquals(page, clienteRestController.index(1));
	}
	
	@Test
	public void testShow() {
		Long id = 1L;
		
		when(clienteService.findById(ArgumentMatchers.anyLong())).thenReturn(cliente);
		assertEquals(responseEntity, clienteRestController.show(id));
	}
	
	@Test
	public void testUpdate() {
		Long id = 1L;
		
		when(clienteService.findById(ArgumentMatchers.anyLong())).thenReturn(cliente);
		when(clienteService.save(ArgumentMatchers.any(Cliente.class))).thenReturn(cliente);
		assertEquals(HttpStatus.CREATED, clienteRestController.update(cliente, bindingResult, id).getStatusCode());
	}
}
