package internet.shop.controller.simple;

import internet.shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = {"/home", "/"})
    public ModelAndView getHomepage() {
        ModelAndView modelAndView = new ModelAndView("home");
        Map<String, Object> model = modelAndView.getModel();
        model.put("user", userService.getCurrentUser());
        return modelAndView;
    }
}
