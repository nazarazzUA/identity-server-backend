package nz.com.identity.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String getIndexPage()
    {
        return "index";
    }

    @GetMapping("/error")
    public String getErrorPage() { return "error"; }
}
