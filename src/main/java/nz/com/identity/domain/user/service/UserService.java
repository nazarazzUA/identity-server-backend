package nz.com.identity.domain.user.service;

import nz.com.identity.domain.user.User;
import nz.com.identity.domain.user.repository.UserRepository;
import nz.com.identity.domain.user.exception.UserNotFoundException;
import nz.com.identity.domain.user.requests.UserCreateRequest;
import nz.com.identity.domain.user.requests.UserCredentialsRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public Page<User> all()  {
        return (Page<User>) userRepository.findAll(PageRequest.of(0,  10));
    }

    public User getById(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public User create(UserCreateRequest request) {

        User user = new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPatronymic(request.getPatronymic());
        user.setPassword(encoder.encode(request.getPassword()));

        this.userRepository.save(user);

        return user;
    }

    public User delete(Long id) throws UserNotFoundException {

        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);

        return user;
    }

    public User update(Long id, UserCredentialsRequest request) throws UserNotFoundException {

        User user = userRepository
                .findById(id)
                .map((User element) -> {
                    element.setPassword(request.getPassword());
                    element.setEmail(request.getEmail());
                    return element;
                }).orElseThrow(UserNotFoundException::new);

        userRepository.save(user);

        return user;
    }

}
