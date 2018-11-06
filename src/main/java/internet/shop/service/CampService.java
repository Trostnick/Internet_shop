package internet.shop.service;

import internet.shop.entity.Camp;
import internet.shop.filter.CampFilter;
import internet.shop.repository.CampRepository;
import internet.shop.repository.CampTypeRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Service
public class CampService {

    @PersistenceContext
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


    @RolesAllowed("manager")
    public Camp add(Camp newCamp) {
        campRepository.save(newCamp);
        return newCamp;
    }

    public void deleteOne(Long id) throws ObjectNotFoundException {
        Camp removedCamp = campRepository.getByIdAndRemovedFalse(id);
        if (removedCamp == null) throw new ObjectNotFoundException(id, "Camp");
        removedCamp.setRemoved(true);
        campRepository.save(removedCamp);
    }

    public Camp put(Long id, Camp newCamp) throws ObjectNotFoundException {
        if (!campRepository.existsByIdAndRemovedFalse(id)) throw new ObjectNotFoundException(id, "Camp");
        newCamp.setId(id);
        campRepository.save(newCamp);
        return newCamp;
    }

    public Camp getOne(Long id) throws ObjectNotFoundException {
        Camp curCamp = campRepository.getByIdAndRemovedFalse(id);
        if (curCamp == null) throw new ObjectNotFoundException(id, "Camp");
        return curCamp;
    }

    public List<Camp> getMany(CampFilter campFilter) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Camp> query = cb.createQuery(Camp.class);
        Root<Camp> root = query.from(Camp.class);
        query.select(root);


        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.notEqual(root.get("removed"), true));

        if (!(campFilter.getName() == null || campFilter.getName().isEmpty())) {
            predicates.add(cb.like(root.get("name"), "%" + campFilter.getName() + "%"));
        }
        if (!(campFilter.getAgeMin() == null)) {
            predicates.add(cb.ge(root.get("ageMin"), campFilter.getAgeMin()));
        }
        if (!(campFilter.getAgeMax() == null)) {
            predicates.add(cb.le(root.get("ageMax"), campFilter.getAgeMax()));
        }
        if (!(campFilter.getDateStart() == null || campFilter.getDateStart().isEmpty())) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("dateStart"), campFilter.getDateStartAsLocalDate()));
        }
        if (!(campFilter.getDateFinish() == null || campFilter.getDateFinish().isEmpty())) {
            predicates.add(cb.lessThanOrEqualTo(root.get("dateFinish"), campFilter.getDateFinishAsLocalDate()));
        }
        if (!(campFilter.getPlace() == null || campFilter.getPlace().isEmpty())) {
            predicates.add(cb.equal(root.get("place").get("name"), campFilter.getPlace()));
        }
        if (!(campFilter.getType() == null || campFilter.getType().isEmpty())) {
            predicates.add(cb.equal(root.get("type").get("name"), campFilter.getType()));
        }

        query.where(predicates.toArray(new Predicate[predicates.size()]));

        return em.createQuery(query).getResultList();
    }

    /*public Camp putIcon(Camp curCamp, String iconPath)throws IOException {
        File icon = new File(iconPath);
        curCamp.setIcon(Files.readAllBytes(icon.toPath()));
        campRepository.save(curCamp);
        return curCamp;
    }*/
}
