package internet.shop.service;

import internet.shop.constant.*;
import internet.shop.entity.Camp;
import internet.shop.entity.CampType;
import internet.shop.exception.FindByIdException;
import internet.shop.repository.CampRepository;
import internet.shop.repository.CampTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CampService {

    private final CampRepository campRepository;

    private final CampTypeRepository campTypeRepository;

    private final PlaceService placeService;

    @Autowired
    public CampService(CampRepository campRepository, CampTypeRepository campTypeRepository,
                       PlaceService placeService) {
        this.campRepository = campRepository;
        this.campTypeRepository = campTypeRepository;
        this.placeService = placeService;
    }

    private Camp findOne(Long id) throws FindByIdException {
        Optional<Camp> camp = campRepository.findById(id);
        if (!camp.isPresent()) {
            throw new FindByIdException("Camp not found");
        }
        Camp curCamp = camp.get();
        if (curCamp.getType().getId().equals(CAMP_TYPE.REMOVED.getValue())){
            throw new FindByIdException("Camp was removed");
        }
        return  camp.get();
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
                    camp.setPlace(placeService.getOne(Long.parseLong(current[1])));
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
        Camp campRemoved = findOne(id);
        CampType removedType = campTypeRepository.findById(CAMP_TYPE.REMOVED.getValue()).get();
        campRemoved.setType(removedType);
        campRepository.save(campRemoved);
    }

    public void put(Long id, String params){
        Camp curCamp=findOne(id);
        addParamsParser(params, curCamp);
        campRepository.save(curCamp);
    }

    public Camp getOne(Long id){
        return findOne(id);
    }

    /*public String getMany (){
        List<Camp> allCamps = campRepository.findAll();
        StringBuilder result = new StringBuilder();
        for(Camp camp: allCamps){
            result.append(camp.toString());
        }
        return result.toString();
    }*/


}
