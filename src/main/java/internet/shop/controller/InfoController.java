package internet.shop.controller;

import internet.shop.entity.Camp;
import internet.shop.repository.CampRepository;
import internet.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.Map;

@Controller
public class InfoController {

    private final CampRepository campRepository;

    private final UserService userService;

    @Autowired
    public InfoController(CampRepository campRepository, UserService userService) {
        this.campRepository = campRepository;
        this.userService = userService;
    }

    @GetMapping(value = {"/home", "/"})
    public ModelAndView getHomepage() {
        ModelAndView modelAndView = new ModelAndView("home");
        Map<String, Object> model = modelAndView.getModel();

        model.put("user", userService.getCurrentUser());
        model.put("camps", campRepository.getAllByRemovedFalse());
        return modelAndView;
    }

    @GetMapping("/camp/{id}")
    public ModelAndView getCamppage(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("camp");
        Map<String, Object> model = modelAndView.getModel();
        Camp curCamp = campRepository.getByIdAndRemovedFalse(id);
        if (curCamp.getInfo().isEmpty()) {
            curCamp.setInfo(null);
        }
        model.put("camp", curCamp);
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView autorization(@RequestParam(required = false) String error,
                                     @RequestParam(required = false) String logout) {
        if (!(userService.getCurrentUser() == null)) {
            return new ModelAndView("redirect:/home");
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
