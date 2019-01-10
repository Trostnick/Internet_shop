package internet.shop.controller.simple;

import internet.shop.repository.CampTypeRepository;
import internet.shop.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.Map;


@RolesAllowed({"manager", "admin"})
@Controller
public class AddController {

    private final PlaceService placeService;

    private final CampTypeRepository campTypeRepository;

    @Autowired
    public AddController(PlaceService placeService, CampTypeRepository campTypeRepository) {
        this.placeService = placeService;
        this.campTypeRepository = campTypeRepository;
    }


    @GetMapping("/camp/add")
    public ModelAndView addCamp() {
        ModelAndView modelAndView = new ModelAndView("addCamp");
        Map<String, Object> model = modelAndView.getModel();
        model.put("placeList", placeService.getMany(""));
        model.put("typeList", campTypeRepository.getAllByNameNotNull());
        return modelAndView;
    }


    @GetMapping("/place/add")
    public ModelAndView addPlace() {
        ModelAndView modelAndView = new ModelAndView("addPlace");
        Map<String, Object> model = modelAndView.getModel();
        return modelAndView;
    }
}
