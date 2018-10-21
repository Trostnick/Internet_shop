package internet.shop.service;

import internet.shop.entity.Camp;
import internet.shop.exception.FindByIdException;
import internet.shop.repository.CampRepository;
import internet.shop.repository.CampTypeRepository;
import internet.shop.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CampService {

    private final CampRepository campRepository;

    private final CampTypeRepository campTypeRepository;

    private final PlaceRepository placeRepository;

    @Autowired
    public CampService(CampRepository campRepository, CampTypeRepository campTypeRepository,
                       PlaceRepository placeRepository) {
        this.campRepository = campRepository;
        this.campTypeRepository = campTypeRepository;
        this.placeRepository = placeRepository;
    }

    private Camp findOne(Long id) throws FindByIdException {
        Optional<Camp> camp = campRepository.findById(id);
        if (!camp.isPresent()) {
            throw new FindByIdException("Place not found");
        }
        return camp.get();
    }

    private void addParamsParser(String params, Camp camp) {
        for (String param : params.split(",")) {
            String[] current = param.split("=");

            switch (current[0].replaceAll(" ", "")) {
                case "name":
                    camp.setName(current[1]);
                    break;
                case "dateStart":
                    camp.setDateStart(LocalDate.parse(current[1]));
                    break;
                case "dateFinish":
                    camp.setDateFinish(LocalDate.parse(current[1]));
                    break;
                case "type_id":
                    campTypeRepository.findById(Long.parseLong(current[1])).ifPresent(camp::setType);
                    break;
                case "place_id":
                    placeRepository.findById(Long.parseLong(current[1])).ifPresent(camp::setPlace);
                    break;
                case "childrenCount":
                    camp.setChildrenCount(Integer.parseInt(current[1]));
                    break;
                case "ageMin":
                    camp.setAgeMin(Integer.parseInt(current[1]));
                    break;
                case "ageMax":
                    camp.setAgeMax(Integer.parseInt(current[1]));
                    break;
                case "info":
                    camp.setInfo(current[1]);
                    break;
                case "icon":
                    camp.setIcon(current[1]);
                    break;
            }
        }
    }

    public void add(String params) {
        Camp newCamp = new Camp();
        addParamsParser(params, newCamp);
        campRepository.save(newCamp);
    }

    public void deleteOne(Long id) {
        findOne(id);
        campRepository.deleteById(id);
    }

    public void put(Long id, String params){
        Camp curCamp=findOne(id);
        addParamsParser(params, curCamp);
        campRepository.save(curCamp);
    }

    public String getOne(Long id){
        Camp curCamp = findOne(id);
        return curCamp.toString();
    }

    public String getMany (){
        List<Camp> allCamps = campRepository.findAll();
        StringBuilder result = new StringBuilder();
        for(Camp camp: allCamps){
            result.append(camp.toString());
        }
        return result.toString();
    }


}
