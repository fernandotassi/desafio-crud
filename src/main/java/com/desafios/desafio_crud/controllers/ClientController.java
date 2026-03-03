package com.desafios.desafio_crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.desafios.desafio_crud.dto.ClientDTO;
import com.desafios.desafio_crud.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientController 
{
	@Autowired
	private ClientService servico;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> findById(@PathVariable Long id)
	{
		ClientDTO dto = servico.findById(id);
		return ResponseEntity.ok(dto);
	}
}
