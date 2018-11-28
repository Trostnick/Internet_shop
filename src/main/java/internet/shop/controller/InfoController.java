package internet.shop.controller;

import internet.shop.model.entity.Camp;
import internet.shop.repository.CampRepository;
import internet.shop.service.CampPhotoService;
import internet.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller

public class InfoController {

    private final CampRepository campRepository;

    private final UserService userService;

    private final CampPhotoService campPhotoService;

    @Autowired
    public InfoController(CampRepository campRepository, UserService userService, CampPhotoService campPhotoService) {
        this.campRepository = campRepository;
        this.userService = userService;
        this.campPhotoService = campPhotoService;
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
        List<Long> photoIdList = campPhotoService.getAllIdByCampId(id);
        if (!(photoIdList.isEmpty())){
            model.put("firstPhotoId", photoIdList.get(0));
            if (photoIdList.size()>1){
                List<Integer> idList = new ArrayList<>();
                for (int i=1; i<photoIdList.size(); i++){
                    idList.add(i);
                }
                model.put("idList", idList);
                photoIdList.remove(0);
                model.put("photoList", photoIdList);
            }
        }
        return modelAndView;
    }


}
