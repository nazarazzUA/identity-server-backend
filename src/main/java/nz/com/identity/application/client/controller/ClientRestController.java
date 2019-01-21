package nz.com.identity.application.client.controller;

import nz.com.identity.domain.client.Client;
import nz.com.identity.domain.client.ClientService;
import nz.com.identity.domain.client.requests.ClientRequest;
import nz.com.identity.domain.user.User;
import nz.com.identity.domain.user.requests.UserCreateRequest;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

@RestController
public class ClientRestController {

    private ClientService clientService;

    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/clients")
    Client create(@RequestBody @Valid ClientRequest request) throws NoSuchProviderException, NoSuchAlgorithmException {
        return this.clientService.create(request);
    }

}
