package internet.shop.controller;

import internet.shop.entity.Camp;
import internet.shop.repository.CampTypeRepository;
import internet.shop.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class AddController {

    private final PlaceService placeService;

    private final CampTypeRepository campTypeRepository;

    @Autowired
    public AddController(PlaceService placeService, CampTypeRepository campTypeRepository) {
        this.placeService = placeService;
        this.campTypeRepository = campTypeRepository;
    }

    @GetMapping("/camp")
    public ModelAndView addCamp() {
        ModelAndView modelAndView = new ModelAndView("addCamp");
        Map<String, Object> model = modelAndView.getModel();
        model.put("places", placeService.getMany(""));
        model.put("types", campTypeRepository.getAllByNameNotNull());
        return modelAndView;
    }

    @GetMapping("/place")
    public ModelAndView addPlace() {
        ModelAndView modelAndView = new ModelAndView("addPlace");
        Map<String, Object> model = modelAndView.getModel();
        return modelAndView;
    }
}
