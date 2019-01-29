package nz.com.identity.application;

import com.github.javafaker.Faker;
import nz.com.identity.domain.client.Client;
import nz.com.identity.domain.client.ClientService;
import nz.com.identity.domain.client.requests.ClientRequest;
import nz.com.identity.domain.user.service.UserService;
import nz.com.identity.domain.user.requests.UserCreateRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class InitialAppRunner implements CommandLineRunner {

    private UserService userService;
    private ClientService clientService;

    public InitialAppRunner(UserService userService, ClientService clientService) {
        this.userService = userService;
        this.clientService = clientService;
    }

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();

        for (int i = 0; i < 10; i++ ) {

            UserCreateRequest request = new UserCreateRequest();

            request.setEmail(faker.internet().emailAddress());
            request.setPassword(faker.internet().password());
            request.setFirstName(faker.name().firstName());
            request.setLastName(faker.name().lastName());
            request.setPatronymic(faker.name().name());

            userService.create(request);
        }


        Random r = new Random();
        int low = 30;
        int high = 3600;

        for ( int i = 0; i < 3; i++) {
            int result = r.nextInt(high-low) + low;

            ClientRequest request = new ClientRequest(
                    faker.internet().uuid(),
                    Client.PUBLIC,
                    faker.internet().uuid(),
                    "Cli application",
                    result,
                    86400,
                    null,
                    null,
                    null
            );

            clientService.create(request);
        }
    }
}
