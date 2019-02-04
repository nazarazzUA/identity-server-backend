package nz.com.identity.domain.client;

import nz.com.identity.domain.client.exception.ClientNotFoundException;
import nz.com.identity.domain.client.requests.ClientRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Date;

@Service
public class ClientService {

    private ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public Client getById(Long id) throws ClientNotFoundException {
        return repository.findById(id).orElseThrow(ClientNotFoundException::new);
    }

    public Client create(ClientRequest request) throws Exception {

        String secretKey = null;

        if (request.getClientType().equals(Client.CONFIDENTIAL)) {
            secretKey = getRandomKey();
        }

        Client client = new Client(
                request.getClientId(),
                secretKey,
                request.getClientType(),
                request.getAllowedDomain(),
                request.getAllowedClientIps(),
                request.getAllowedRedirectUrls(),
                request.getAccessTokenTtl(),
                request.getRefreshTokenTtl()
        );

        repository.save(client);

        return client;
    }

    public Page<Client> all() {
        return (Page<Client>) repository.findAll(PageRequest.of(0,  10));
    }

    public Client delete(Long id) throws ClientNotFoundException {

        Client client = repository.findById(id).orElseThrow(ClientNotFoundException::new);
        repository.deleteById(id);

        return client;
    }

    public Client update(Long id, ClientRequest request) throws ClientNotFoundException {

        return repository.findById(id).map((Client element) -> {
            element.setAccessTokenTtl(request.getAccessTokenTtl());
            element.setRefreshTokenTtl(request.getRefreshTokenTtl());
            element.setAllowedClientIps(request.getAllowedClientIps());
            element.setAllowedDomain(request.getAllowedDomain());
            element.setAllowedRedirectUrls(request.getAllowedRedirectUrls());
            element.setUpdatedAt(new Date());

            return element;
        }).orElseThrow(ClientNotFoundException::new);
    }

    private String getRandomKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        SecretKey secretKey = keyGen.generateKey();

        return Arrays.toString(secretKey.getEncoded());
    }

}
