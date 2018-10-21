package internet.shop.service;

import internet.shop.entity.Place;
import internet.shop.exception.FindByIdException;
import internet.shop.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

    @Autowired
    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    private void addParamsParser(String params, Place place) {
        for (String param : params.split(";")) {
            String[] current = param.split("=");

            switch (current[0].replaceAll(" ", "")) {
                case "name":
                    place.setName(current[1]);
                    break;
                case "info":
                    place.setInfo(current[1]);
                    break;
                case "address":
                    place.setAddress(current[1]);
                    break;


            }
        }
    }

    private Place findOne(Long id) throws FindByIdException {
        Optional<Place> place = placeRepository.findById(id);
        if (!place.isPresent()) {
            throw new FindByIdException("Place not found");
        }
        return place.get();
    }

    public void add(String params) {
        Place newPlace = new Place();
        addParamsParser(params, newPlace);
        placeRepository.save(newPlace);
    }

    public void deleteOne(Long id) {
        findOne(id);
        placeRepository.deleteById(id);
    }

    public void put(Long id, String params) {
        Place curPlace = findOne(id);
        addParamsParser(params, curPlace);
        placeRepository.save(curPlace);
    }

    public String getOne(Long id) {
        Place curPlace = findOne(id);
        return curPlace.toString();
    }

    public String getMany() {
        List<Place> allPlaces = placeRepository.findAll();
        StringBuilder result = new StringBuilder();
        for (Place place : allPlaces) {
            result.append(place.toString());
        }
        return result.toString();
    }
    /*public String get*/
}
