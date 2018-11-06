package internet.shop.controller;

import internet.shop.repository.CampRepository;
import internet.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.Map;

@Controller
public class InfoController {

    private final CampRepository campRepository;

    private final UserRepository userRepository;

    @Autowired
    public InfoController(CampRepository campRepository, UserRepository userRepository) {
        this.campRepository = campRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(value = {"/home", "/"})
    public ModelAndView getHomepage() {

        ModelAndView modelAndView = new ModelAndView("home");
        Map<String, Object> model = modelAndView.getModel();

        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            internet.shop.entity.User curUser = userRepository
                    .getByLoginAndRemovedFalse(user.getUsername());
            model.put("username", curUser.getName());
        } catch (ClassCastException e) {
            model.put("notAuthoraized", e);
        }


        model.put("camps", campRepository.getAllByRemovedFalse());

        return modelAndView;
    }

    @GetMapping("/camp/{id}")
    public ModelAndView getCamppage(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("camp");
        Map<String, Object> model = modelAndView.getModel();

        /*User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        internet.shop.entity.User curUser = userRepository
                .getByLoginAndRemovedFalse(user.getUsername());*/

        model.put("camp", campRepository.getByIdAndRemovedFalse(id));
        /*model.put("username", curUser.getName());*/


        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView autorization(@RequestParam(required = false) String error,
                                     @RequestParam(required = false) String logout) {
        ModelAndView modelAndView = new ModelAndView("login");
        Map<String, Object> model = modelAndView.getModel();
        model.put("error", error);
        model.put("logout", logout);
        return modelAndView;

    }


}
