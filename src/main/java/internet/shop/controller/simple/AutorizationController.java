package internet.shop.controller.simple;

import internet.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class AutorizationController {

    private final UserService userService;

    @Autowired
    public AutorizationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView autorization(@RequestParam(required = false) String error,
                                     @RequestParam(required = false) String logout) {
        if (!(userService.getCurrentUser() == null)) {
            return new ModelAndView("forward:/home");
        }
        ModelAndView modelAndView = new ModelAndView("login");
        Map<String, Object> model = modelAndView.getModel();
        model.put("error", error);
        model.put("logout", logout);
        return modelAndView;
    }

    @GetMapping("/accessDenied")
    public String getAccessDeniedPage() {
        return "accessDenied";
    }
}
