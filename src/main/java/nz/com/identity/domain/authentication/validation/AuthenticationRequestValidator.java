package nz.com.identity.domain.authentication.validation;

import nz.com.identity.domain.authentication.request.AuthenticationRequest;
import nz.com.identity.domain.authentication.request.values.DisplayTypes;
import nz.com.identity.domain.authentication.request.values.Prompts;
import nz.com.identity.domain.authentication.request.values.ResponseTypes;
import nz.com.identity.domain.client.Client;
import nz.com.identity.domain.client.ClientRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public class AuthenticationRequestValidator {

    private Validator validator;
    private ClientRepository repository;
    private DisplayTypes displayTypes;
    private Prompts prompts;
    private ResponseTypes responseTypes;

    public AuthenticationRequestValidator(
        Validator validator,
        ClientRepository repository
    ) {
        this.validator = validator;
        this.repository = repository;
    }

    public boolean validate(AuthenticationRequest request) {
        Set<ConstraintViolation<Object>> validationResult = this.validator.validate(request);

        if (!validationResult.isEmpty()) {
            return false;
        }

        Client client = this.repository.findByClientId(request.getClientId());

        return true;
    }

}
