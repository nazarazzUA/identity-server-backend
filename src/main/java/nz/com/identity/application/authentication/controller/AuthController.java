package nz.com.identity.application.authentication.controller;

import nz.com.identity.domain.user.User;
import nz.com.identity.domain.authorization.service.UserAuthorizationService;
import nz.com.identity.domain.user.requests.UserCredentialsRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AuthController
{
    @Autowired
    private UserAuthorizationService userService;
    private final Log logger = LogFactory.getLog(this.getClass());

    @GetMapping("/authorization")
    public String getLoginPage(Model model)
    {
        model.addAttribute("loginRequest", new UserCredentialsRequest());
        return "login";
    }

    @GetMapping("/account/register")
    public String getRegistrationPage(Model model)
    {
        model.addAttribute("registrationRequest", new UserCredentialsRequest());
        return "register";
    }

    @PostMapping("/account/register")
    public String registrationProcess(
            @Valid @ModelAttribute UserCredentialsRequest registrationRequest, Model model
    ) {
        model.addAttribute("registrationRequest", registrationRequest);

        try {

            User user = this.userService.registerNewAccount(registrationRequest);

        } catch (Exception exception) {
            this.logger.error(exception.getLocalizedMessage());
            return "register";
        }
        return "redirect:/";
    }
}
