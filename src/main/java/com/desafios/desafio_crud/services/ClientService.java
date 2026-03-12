package com.desafios.desafio_crud.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.desafios.desafio_crud.dto.ClientDTO;
import com.desafios.desafio_crud.entities.Client;
import com.desafios.desafio_crud.repositories.ClientRepository;
import com.desafios.desafio_crud.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientService 
{
	@Autowired
	private ClientRepository repository;
	
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id)
	{		 
		Client client = repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Cliente inexistente"));
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
		copyDtoToClient(client, dto); 
		repository.save(client);
		return new ClientDTO(client);
	}
	
	@Transactional
	public ClientDTO update(Long id, ClientDTO dto)
	{
		try 
		{	
			Client client = repository.getReferenceById(id);
			copyDtoToClient(client, dto);
			client = repository.save(client);
			return new ClientDTO(client);
		}
		catch (EntityNotFoundException e)
		{throw new ResourceNotFoundException("Cliente inexistente");}
	}
	
	@Transactional
	public void delete(Long id)
	{
		if(!repository.existsById(id))
			throw new ResourceNotFoundException("Cliente inexistente");
		repository.deleteById(id);
	}
	
	private void copyDtoToClient(Client client, ClientDTO dto)
	{
		client.setName(dto.getName());
		client.setCpf(dto.getCpf());
		client.setIncome(dto.getIncome());
		client.setBirthDate(dto.getBirthDate());
		client.setChildren(dto.getChildren());
	}
}
