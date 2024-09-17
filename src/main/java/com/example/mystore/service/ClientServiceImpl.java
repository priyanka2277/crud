package com.example.mystore.service;

import com.example.mystore.controller.model.Client;
import com.example.mystore.repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientsRepository clientsRepository;
    @Override
    public List<Client> getAllClient(){
        return clientsRepository.findAll();
    }
    @Override
    public Optional<Client> getClientById(int id){
        return clientsRepository.findById(id);
    }
    @Override
    public Client saveClient(Client client){
        return clientsRepository.save(client);
    }
    @Override
    public Client updateClient(Client client){
        if(clientsRepository.existsById(client.getId())){
            return clientsRepository.save(client);
        }else{
            throw new RuntimeException("Client not found with id"+client.getId());
        }
    }
    @Override
    public void deleteClient(int id){
        if(clientsRepository.existsById(id)){
            clientsRepository.deleteById(id);
        }else{
            throw new RuntimeException("Client not found with id"+id);
        }
    }


}
