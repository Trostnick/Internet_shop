package internet.shop.controller;

import internet.shop.filter.CampFilter;
import internet.shop.service.CampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class FilterController {

    private final CampService campService;

    @Autowired
    public FilterController(CampService campService){
        this.campService=campService;
    }

    @GetMapping("/filter/camp")
    public ModelAndView getFilterCamp(CampFilter campFilter){
        ModelAndView modelAndView = new ModelAndView("filterCamp");
        Map<String, Object> model= modelAndView.getModel();
        if (campFilter==null){
            model.put("filterCamp",campService.getMany(new CampFilter()));
            return modelAndView;
        }
        model.put("filterCamp", campService.getMany(campFilter));
        return modelAndView;
    }
}
