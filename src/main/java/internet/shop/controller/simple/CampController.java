package internet.shop.controller.simple;

import internet.shop.model.entity.Camp;
import internet.shop.model.entity.CampType;
import internet.shop.model.entity.Place;
import internet.shop.model.entity.User;
import internet.shop.repository.CampTypeRepository;
import internet.shop.service.CampPhotoService;
import internet.shop.service.CampService;
import internet.shop.service.PlaceService;
import internet.shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class CampController {

    private final UserService userService;

    private final PlaceService placeService;

    private final CampService campService;

    private final CampPhotoService campPhotoService;

    private final CampTypeRepository campTypeRepository;

    public CampController(UserService userService, PlaceService placeService, CampTypeRepository campTypeRepository,
                          CampService campService, CampPhotoService campPhotoService) {
        this.userService = userService;
        this.placeService = placeService;
        this.campTypeRepository = campTypeRepository;
        this.campService = campService;
        this.campPhotoService = campPhotoService;
    }

    @GetMapping("/camp/{id}")
    public ModelAndView getCamppage(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("camp");
        Map<String, Object> model = modelAndView.getModel();
        Camp curCamp = campService.getOne(id);
        if (curCamp.getInfo().isEmpty()) {
            curCamp.setInfo(null);
        }
        model.put("camp", curCamp);
        List<Long> photoIdList = campPhotoService.getAllIdByCampId(id);
        if (!(photoIdList.isEmpty())) {
            model.put("firstPhotoId", photoIdList.get(0));
            if (photoIdList.size() > 1) {
                List<Integer> idList = new ArrayList<>();
                for (int i = 1; i < photoIdList.size(); i++) {
                    idList.add(i);
                }
                model.put("idList", idList);
                photoIdList.remove(0);
                model.put("photoList", photoIdList);
            }
        }

        if(curCamp.getUser() == userService.getCurrentUser()){
            model.put("is_my", true);
        }
        else{
            model.put("is_my", false);
        }
        return modelAndView;
    }

    @GetMapping("/camp/edit/{id}")
    @RolesAllowed({"admin", "manager"})
    public ModelAndView getEditCampPage(@PathVariable Long id){
        Camp curCamp = campService.getOne(id);

        //В случае, если текущий пользователь не создавал этот лагерь
        try{
            User curUser = userService.getCurrentUser();
        if (curUser.getStatus().getName().equals("admin") || !(curCamp.getUser().getId().equals(curUser.getId()))){
            return new ModelAndView("forward:/accessDenied");
        }
        }catch (Exception e){
            return new ModelAndView("forward:/accessDenied");
        }

        ModelAndView modelAndView = new ModelAndView("editCamp");
        Map<String, Object> model = modelAndView.getModel();
        model.put("camp", curCamp);
        model.put("placeList", placeService.getMany(""));
        model.put("typeList",campTypeRepository.getAllByNameNotNull());
        return modelAndView;
    }

}
