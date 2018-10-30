package internet.shop.service;

import internet.shop.constant.*;
import internet.shop.entity.Camp;
import internet.shop.entity.CampType;
import internet.shop.exception.FindByIdException;
import internet.shop.repository.CampRepository;
import internet.shop.repository.CampTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

@Service
public class CampService {

/*    @PersistenceContext
    private EntityManager em;

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




    public void add(Camp camp) {
        Camp newCamp = new Camp();
        campRepository.save(newCamp);
    }

    public void deleteOne(Long id) {
        Camp campRemoved = findOne(id);
        campRemoved.setType(removedType);
        campRepository.save(campRemoved);
    }

    public void put(Long id, String params) {
        Camp curCamp = findOne(id);
        addParamsParser(params, curCamp);
        campRepository.save(curCamp);
    }

    public Camp getOne(Long id) {
        return findOne(id);
    }

    public List<Camp> getMany(String name, int ageMin, int ageMax) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Camp> query = cb.createQuery(Camp.class);
        Root<Camp> root = query.from(Camp.class);
        query.select(root);

        CampType removedType = campTypeRepository.findById(CAMP_TYPE.REMOVED.getValue()).get();

        Predicate criteriaName = cb.like(root.get("name"), "%" + name + "%");
        Predicate criteriaAgeMin = cb.ge(root.get("ageMin"), ageMin);
        Predicate criteriaAgeMax = cb.le(root.get("ageMax"), ageMax);
        Predicate criteriaRemoved = cb.notEqual(root.get("type"), removedType);

        query.where(cb.and(criteriaRemoved, criteriaName, criteriaAgeMin, criteriaAgeMax));

        return em.createQuery(query).getResultList();
    }*/


}
