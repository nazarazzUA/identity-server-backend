package nz.com.identity.application.client.controller;

import nz.com.identity.domain.client.Client;
import nz.com.identity.domain.client.ClientService;
import nz.com.identity.domain.client.exception.ClientNotFoundException;
import nz.com.identity.domain.client.requests.ClientRequest;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class ClientRestController {

    private ClientService clientService;

    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/clients")
    Client create(@RequestBody @Valid ClientRequest request) throws Exception {
        return clientService.create(request);
    }

    @GetMapping("/clients")
    Page<Client> all() {
        return clientService.all();
    }

    @GetMapping("/clients/{id}")
    Client one(@PathVariable Long id) throws ClientNotFoundException {
        return clientService.getById(id);
    }

    @PutMapping("/clients/{id}")
    Client update(@PathVariable Long id, @RequestBody ClientRequest request) throws ClientNotFoundException {
        return clientService.update(id, request);
    }

    @DeleteMapping("/clients/{id}")
    Client delete(Long id) throws ClientNotFoundException {
        return clientService.delete(id);
    }

}
