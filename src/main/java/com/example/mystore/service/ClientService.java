package com.example.mystore.service;

import com.example.mystore.controller.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> getAllClient();
    Optional<Client> getClientById(int id);
    Client saveClient(Client client);
    Client updateClient(Client client);
    void deleteClient(int id);

}
