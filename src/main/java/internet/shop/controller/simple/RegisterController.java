package internet.shop.controller.simple;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {

    @GetMapping("/registration")
    public String getRegistrationPage() {
        return "registration";
    }
}
