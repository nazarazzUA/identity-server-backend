package nz.com.identity.domain.authorization.service;

import nz.com.identity.domain.user.User;
import nz.com.identity.domain.user.repository.UserRepository;
import nz.com.identity.domain.user.requests.UserCredentialsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("user_service")
public class UserAuthorizationService implements UserDetailsService {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserAuthorizationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerNewAccount(UserCredentialsRequest registrationRequest) throws Exception
    {
        if (emailExists(registrationRequest.getUsername())) {
            throw new Exception("Email exists");
        }

        User registerUser = new User();

        registerUser.setEmail(registrationRequest.getUsername());
        registerUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

        this.userRepository.save(registerUser);

        Authentication auth = new UsernamePasswordAuthenticationToken(
                registerUser, null, new ArrayList<GrantedAuthority>()
        );

        SecurityContextHolder.getContext().setAuthentication(auth);

        return registerUser;
    }

    private boolean emailExists(String email)
    {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user " + email);
        }

        return user;
    }
}
