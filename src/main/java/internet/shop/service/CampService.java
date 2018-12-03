package internet.shop.service;

import internet.shop.exception.ValidationException;
import internet.shop.model.entity.Camp;
import internet.shop.model.entity.CampType;
import internet.shop.model.entity.Place;
import internet.shop.model.filter.CampFilter;
import internet.shop.model.form.CampForm;
import internet.shop.repository.CampRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CampService {

    @PersistenceContext
    private EntityManager em;

    private final CampRepository campRepository;

    private final CampPhotoService campPhotoService;

    @Autowired
    public CampService(CampRepository campRepository, CampPhotoService campPhotoService) {
        this.campRepository = campRepository;
        this.campPhotoService = campPhotoService;
    }

    private Camp convertToCamp(CampForm form) {
        Camp newCamp = new Camp();
        newCamp.setRemoved(false);
        newCamp.setInfo(form.getInfo());
        newCamp.setName(form.getName());
        newCamp.setAgeMin(form.getAgeMin());
        newCamp.setAgeMax(form.getAgeMax());
        newCamp.setChildrenCount(form.getChildrenCount());
        newCamp.setPrice(form.getPrice());
        newCamp.setDateStart(form.getDateStart());
        newCamp.setDateFinish(form.getDateFinish());

        MultipartFile icon = form.getIcon();
        try {
            List<byte[]> iconList = campPhotoService.resizeToIconAndSmallIcon(icon.getInputStream(), icon.getOriginalFilename());
            newCamp.setIcon(iconList.get(0));
            newCamp.setSmallIcon(iconList.get(1));
        } catch (IOException e) {
            newCamp.setIcon(null);
            newCamp.setSmallIcon(null);
        }
        Place curPlace = new Place();
        curPlace.setId(form.getPlaceId());
        newCamp.setPlace(curPlace);

        CampType curCampType = new CampType();
        curCampType.setId(form.getTypeId());
        newCamp.setType(curCampType);

        return newCamp;
    }


    @Transactional
    public Camp add(CampForm campForm, BindingResult bindingResult) throws ValidationException {
        ValidationException validationException = new ValidationException();

        Camp newCamp = convertToCamp(campForm);

        validationException.add(bindingResult);

        if (newCamp.getDateStart().isBefore(LocalDate.now())) {
            validationException.add("dateStart", "Дата начала лагеря должна быть позднее текущей");
        }

        if (newCamp.getDateFinish().isBefore(LocalDate.now())) {
            validationException.add("dateFinish", "Дата окончания лагеря должна быть позднее текущей");
        }

        if (newCamp.getDateFinish().isBefore(newCamp.getDateStart())) {
            validationException.add("dateFinish", "Дата окончания не может быть раньше даты начала");
        }

        if (newCamp.getAgeMin() > newCamp.getAgeMax()) {
            validationException.add("ageMax", "Максимальный возраст не может быть меньше минимального");
        }


        validationException.throwIf();
        campRepository.save(newCamp);
        campPhotoService.add(campForm.getPhoto(), newCamp);
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
        if (!(campFilter.getAge() == null)) {
            predicates.add(cb.le(root.get("ageMin"), campFilter.getAge()));
            predicates.add(cb.ge(root.get("ageMax"), campFilter.getAge()));
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
        if (!(campFilter.getPriceMin() == null)) {
            predicates.add(cb.ge(root.get("price"), campFilter.getPriceMin()));
        }
        if (!(campFilter.getPriceMax() == null)) {
            predicates.add(cb.le(root.get("price"), campFilter.getPriceMax()));
        }

        query.where(predicates.toArray(new Predicate[predicates.size()]));

        return em.createQuery(query).getResultList();
    }


}
