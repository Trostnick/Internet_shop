package internet.shop.controller.simple;

import internet.shop.model.entity.Camp;
import internet.shop.model.filter.CampFilter;
import internet.shop.model.form.CampPage;
import internet.shop.service.CampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class FilterController {

    private final CampService campService;

    @Autowired
    public FilterController(CampService campService) {
        this.campService = campService;
    }

    @GetMapping("/filter/camp")
    public ModelAndView getFilterCamp(CampFilter campFilter,
                                      @RequestParam(defaultValue = "1", required = false) Long page) {
        ModelAndView modelAndView = new ModelAndView("filterCamp");
        Map<String, Object> model = modelAndView.getModel();
        if (campFilter == null){
            campFilter = new CampFilter();
        }
        CampPage campPage = campService.getManyOnPage(campFilter, page);
        List<Camp> campList = campPage.getCampList();
        if (!(campList.isEmpty())) {
            model.put("campList", campList);
        }
        model.put("page", page);
        Long pageCount = campPage.getPageCount();
        if(page>1){
            if (page>2) {
                if (page > 3){
                    model.put("notInStart", true);
                }
                model.put("firstPage", 1);
            }
            model.put("prevPage", page-1);
        }


        if (page < pageCount){
            if (page<pageCount-1) {
                if (page<pageCount-2){
                    model.put("notInFinish", true);
                }

                model.put("lastPage", pageCount);
            }
            model.put("nextPage", page+1);
        }
        model.put("campFullCount",campService.getAllCount() );
        model.put("campCount", campPage.getCampCount());
        return modelAndView;
    }
}
