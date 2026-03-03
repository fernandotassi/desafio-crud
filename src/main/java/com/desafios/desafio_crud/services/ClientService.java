package com.desafios.desafio_crud.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.desafios.desafio_crud.dto.ClientDTO;
import com.desafios.desafio_crud.entities.Client;
import com.desafios.desafio_crud.repositories.ClientRepository;

@Service
public class ClientService 
{
	@Autowired
	private ClientRepository repository;
	
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id)
	{
		Optional<Client> clientDto = repository.findById(id);
		Client client = clientDto.get();
		return new ClientDTO(client);
	}
}
