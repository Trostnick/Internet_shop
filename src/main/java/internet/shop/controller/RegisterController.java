package internet.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class RegisterController {

    @GetMapping("/registration")
    public String getRegistrationPage() {
        return "registration";
    }
}
