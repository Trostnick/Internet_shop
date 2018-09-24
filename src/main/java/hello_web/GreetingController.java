package hello_web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;


@Controller
@RestController
public class GreetingController {

    @Autowired
    private CampRepository campRepository;

    @RequestMapping("/findById")
    public String getCampByID(@RequestParam(value = "ID", defaultValue = "1") String id) {
        return campRepository.findById(Long.valueOf(id)).get().toString();
    }

    @RequestMapping("/findByName")
    public String getCampByName(@RequestParam(value = "Name", defaultValue = "Vantit") String name) {

        String resCamp = "";

        for (Camp iCamp : campRepository.findAllByName(name)) {
            resCamp = resCamp.concat(iCamp.toString());
            resCamp = resCamp.concat("\n");
        }

        return resCamp;
    }

    @RequestMapping("/all")
    public String getCampAll() {

        String resCamp = "";

        for (Camp iCamp : campRepository.findAll()) {
            resCamp = resCamp.concat(iCamp.toString());
            resCamp = resCamp.concat("\n");
        }

        return resCamp;
    }

    @RequestMapping("/add")
    public String addCamp(@RequestParam String name, @RequestParam String dateStart,
                          @RequestParam String dateFinish) {
        Camp camp = new Camp(name, dateStart, dateFinish);
        campRepository.save(camp);
        return "Saved";
    }
}
