package com.desafios.desafio_crud.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.desafios.desafio_crud.DesafioCrudApplication;
import com.desafios.desafio_crud.dto.ClientDTO;
import com.desafios.desafio_crud.services.ClientService;

import jakarta.servlet.Servlet;

@RestController
@RequestMapping(value = "/clients")
public class ClientController 
{

    private final DesafioCrudApplication desafioCrudApplication;
	@Autowired
	private ClientService servico;

    ClientController(DesafioCrudApplication desafioCrudApplication) {
        this.desafioCrudApplication = desafioCrudApplication;
    }
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> findById(@PathVariable Long id)
	{
		ClientDTO dto = servico.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping
	public ResponseEntity<Page<ClientDTO>> findAll(Pageable pageable)
	{
		Page<ClientDTO> dto = servico.findAll(pageable);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping
	public ResponseEntity<ClientDTO> insert(@RequestBody ClientDTO dto)
	{
		ClientDTO client = servico.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				  .buildAndExpand(client.getId()).toUri();
		return ResponseEntity.created(uri).body(client);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody ClientDTO dto)
	{
		dto = servico.update(id, dto);
		return ResponseEntity.ok(dto);
	}
}
