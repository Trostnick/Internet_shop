package internet.shop.service;

import internet.shop.entity.Place;
import internet.shop.exception.FindByIdException;
import internet.shop.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlaceService {

    @PersistenceContext
    private EntityManager em;

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
        Place curPlace = place.get();
        if (curPlace.isRemoved()){
            throw new FindByIdException("Place was removed");
        }
        return curPlace;
    }

    public void add(String params) {
        Place newPlace = new Place();
        addParamsParser(params, newPlace);
        placeRepository.save(newPlace);
    }

    public void deleteOne(Long id) {
        Place removedPlace = findOne(id);
        removedPlace.setRemoved(true);
        placeRepository.save(removedPlace);
    }

    public void put(Long id, String params) {
        Place curPlace = findOne(id);
        addParamsParser(params, curPlace);
        placeRepository.save(curPlace);
    }

    public Place getOne(Long id) {
        return findOne(id);
    }

    public String getMany(String name) {

        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<Place> query = cb.createQuery(Place.class);
        Root<Place> root = query.from(Place.class);
        query.select(root);

        Predicate criteriaName = cb.like(root.get("name"), "%"+name+"%");
        Predicate criteriaRemoved = cb.equal(root.get("removed"), false);

        query.where(cb.and(criteriaRemoved, criteriaName));

        List<Place> places = em.createQuery(query).getResultList();

        StringBuilder result = new StringBuilder();
        for (Place place : places) {
            result.append(place.toString());
        }
        return result.toString();
    }

}
