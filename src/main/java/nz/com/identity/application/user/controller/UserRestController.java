package nz.com.identity.application.user.controller;

import nz.com.identity.domain.user.User;
import nz.com.identity.domain.user.service.UserService;
import nz.com.identity.domain.user.exception.UserNotFoundException;
import nz.com.identity.domain.user.requests.UserCreateRequest;
import nz.com.identity.domain.user.requests.UserCredentialsRequest;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class UserRestController {

    private UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    Page<User> all() {
        return userService.all();
    }

    @PostMapping("/users")
    User create(@RequestBody @Valid UserCreateRequest request) {
        return this.userService.create(request);
    }

    @GetMapping("/users/{id}")
    User one(@PathVariable Long id) throws UserNotFoundException {
        return this.userService.getById(id);
    }

    @DeleteMapping("/users/{id}")
    User delete(@PathVariable Long id) throws UserNotFoundException {
        return this.userService.delete(id);
    }

    @PutMapping("/users/{id}")
    User update(@PathVariable Long id, @RequestBody UserCredentialsRequest request) throws UserNotFoundException {
        return this.userService.update(id, request);
    }
}
