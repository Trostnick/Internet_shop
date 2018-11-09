package internet.shop.service;

import internet.shop.entity.Place;
import internet.shop.repository.PlaceRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import javax.validation.ConstraintViolation;
import javax.xml.transform.Transformer;
import javax.xml.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PlaceService {

    @PersistenceContext
    private EntityManager em;

    private final PlaceRepository placeRepository;

    @Autowired
    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }


    public Place add(Place newPlace) {

        placeRepository.save(newPlace);
        return newPlace;
    }

    public void deleteOne(Long id) throws ObjectNotFoundException {
        Place removedPlace = placeRepository.getByIdAndRemovedFalse(id);
        if (removedPlace == null) {
            throw new ObjectNotFoundException(id, "Place");
        }
        removedPlace.setRemoved(true);
        placeRepository.save(removedPlace);
    }

    public Place put(Long id, Place newPlace) throws ObjectNotFoundException {
        if (!placeRepository.existsByIdAndRemovedFalse(id)) throw new ObjectNotFoundException(id, "Place");
        newPlace.setId(id);
        placeRepository.save(newPlace);
        return newPlace;

    }

    public Place getOne(Long id) throws ObjectNotFoundException {
        Place curPlace = placeRepository.getByIdAndRemovedFalse(id);
        if (curPlace == null) {
            throw new ObjectNotFoundException(id, "Place");
        }
        return curPlace;
    }

    public List<Place> getMany(String name) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Place> query = cb.createQuery(Place.class);
        Root<Place> root = query.from(Place.class);
        query.select(root);

        List<Predicate> predicates = new ArrayList<>();
        if (!(name == null || name.isEmpty())) {
            predicates.add(cb.like(root.get("name"), "%" + name + "%"));
        }

        predicates.add(cb.notEqual(root.get("removed"), true));

        query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

        return em.createQuery(query).getResultList();
    }
}
