package internet.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import java.util.Map;

@Controller
public class HomeController {

    @GetMapping(value = {"/home", "/"})
    public ModelAndView getHomepage() {
        ModelAndView modelAndView = new ModelAndView("home");
        Map<String, Object> model = modelAndView.getModel();
        model.put("message", "Internet shop");
        return modelAndView;
    }

    @GetMapping("/login")
    public String autorization() {
        return "login";

    }


    @GetMapping("/hello")
    public ModelAndView getHellopage() {
        ModelAndView modelAndView = new ModelAndView("hello");
        Map<String, Object> model = modelAndView.getModel();
        model.put("secret", "Some secret information");
        return modelAndView;
    }


}
