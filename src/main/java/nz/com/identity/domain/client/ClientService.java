package nz.com.identity.domain.client;

import nz.com.identity.domain.client.requests.ClientRequest;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Arrays;

@Service
public class ClientService {

    private ClientRepository repository;

    public Client create(ClientRequest request) throws NoSuchProviderException, NoSuchAlgorithmException {

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

    private String getRandomKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        SecretKey secretKey = keyGen.generateKey();

        return Arrays.toString(secretKey.getEncoded());
    }

}
