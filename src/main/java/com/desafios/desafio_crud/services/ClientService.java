package com.desafios.desafio_crud.services;


import java.awt.font.TransformAttribute;
import java.util.Optional;

import org.apache.tomcat.websocket.Transformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;

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
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAll(Pageable pageable)
	{
		Page<Client> clients = repository.findAll(pageable);
		return clients.map(x -> new ClientDTO(x));
	}
	
	@Transactional
	public ClientDTO insert(ClientDTO dto)
	{
		Client client = new Client();
		transforma(client, dto); 
		repository.save(client);
		return new ClientDTO(client);
	}
	
	@Transactional
	public ClientDTO update(Long id, ClientDTO dto)
	{
		Client client = repository.getReferenceById(id);
		transforma(client, dto);
		client = repository.save(client);
		return new ClientDTO(client);
	}
	
	private void transforma(Client client, ClientDTO dto)
	{
		client.setName(dto.getName());
		client.setCpf(dto.getCpf());
		client.setIncome(dto.getIncome());
		client.setBirthDate(dto.getBirthDate());
		client.setChildren(dto.getChildren());
	}
}
