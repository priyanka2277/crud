package com.example.mystore.controller;

import com.example.mystore.controller.model.Client;
import com.example.mystore.controller.model.ClientDto;
import com.example.mystore.repositories.ClientsRepository;
import com.example.mystore.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @GetMapping
    public String getClients(Model model){
        List<Client> clients=clientService.getAllClient();
        model.addAttribute("clients",clients);
        return "clients/index";

    }
    @GetMapping("/create")
    public String showCreatePage(Model model){
        ClientDto  clientDto=new ClientDto();
        model.addAttribute("clientDto",clientDto);
        return "clients/create";

    }
    @PostMapping("/create")
    public String createClient(
            @Valid @ModelAttribute ClientDto clientDto,
                                       BindingResult result){
        if(result.hasErrors()){
            return "clients/create";
        }
        Client client =new Client();
        client.setFirstName(clientDto.getFirstName());
        client.setEmail(clientDto.getEmail());
        client.setPhone(clientDto.getPhone());
        client.setAddress(clientDto.getAddress());
        client.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        clientService.saveClient(client);

        return "redirect:/clients";
    }
    @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam int id) {
        Optional<Client> clientOptional = clientService.getClientById(id);
        if (clientOptional.isPresent()) {
            model.addAttribute("client", clientOptional.get());
            return "clients/edit";
        } else {
            return "redirect:/clients"; // If client is not found, redirect back
        }
    }
    @PostMapping("/edit")
    public String updateClient(@ModelAttribute Client client) {
        try {
            clientService.updateClient(client);  // This will update the client if the ID exists
            return "redirect:/clients"; // Redirect to the list of clients after successful update
        } catch (RuntimeException e) {
            // Handle exception, e.g., client not found
            return "clients/edit"; // Stay on the edit page if there’s an error
        }
    }
    @GetMapping("/delete")
    public String deleteClient(@RequestParam int id){
        clientService.deleteClient(id);
        return "redirect:/clients";
    }




}
