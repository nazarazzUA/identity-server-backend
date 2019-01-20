package nz.com.identity.application;

import com.github.javafaker.Faker;
import nz.com.identity.domain.user.service.UserService;
import nz.com.identity.domain.user.requests.UserCreateRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitialAppRunner implements CommandLineRunner {

    private UserService userService;

    public InitialAppRunner(UserService userService) {
        this.userService = userService;
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
    }
}
