package internet.shop.service;

import internet.shop.exception.ValidationException;
import internet.shop.model.entity.Camp;
import internet.shop.model.entity.CampType;
import internet.shop.model.entity.Place;
import internet.shop.model.filter.CampFilter;
import internet.shop.model.form.CampForm;
import internet.shop.model.form.CampPage;
import internet.shop.repository.CampRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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

    private final UserService userService;

    private final double defaultPageSize = 10;

    @Autowired
    public CampService(CampRepository campRepository, CampPhotoService campPhotoService,
                       UserService userService) {
        this.campRepository = campRepository;
        this.campPhotoService = campPhotoService;
        this.userService = userService;
    }

    private void resizeAndSetIcon(Camp camp, MultipartFile icon){
        try {
            List<byte[]> iconList = campPhotoService.resizeToIconAndSmallIcon(icon.getInputStream(), icon.getOriginalFilename());
            camp.setIcon(iconList.get(0));
            camp.setSmallIcon(iconList.get(1));
        } catch (IOException | NullPointerException e) {
            camp.setIcon(null);
            camp.setSmallIcon(null);
        }
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

        resizeAndSetIcon(newCamp, form.getIcon());
        Place curPlace = new Place();
        curPlace.setId(form.getPlaceId());
        newCamp.setPlace(curPlace);

        CampType curCampType = new CampType();
        curCampType.setId(form.getTypeId());
        newCamp.setType(curCampType);

        return newCamp;
    }

    private void validateCamp (CampForm campForm, BindingResult bindingResult)throws ValidationException{
        ValidationException validationException = new ValidationException();



        validationException.add(bindingResult);

        if (LocalDate.parse(campForm.getDateStart()).isBefore(LocalDate.now())) {
            validationException.add("dateStart", "Дата начала лагеря должна быть позднее текущей");
        }

        if (LocalDate.parse(campForm.getDateFinish()).isBefore(LocalDate.now())) {
            validationException.add("dateFinish", "Дата окончания лагеря должна быть позднее текущей");
        }

        if (LocalDate.parse(campForm.getDateFinish()).isBefore(LocalDate.parse(campForm.getDateStart()))) {
            validationException.add("dateFinish", "Дата окончания не может быть раньше даты начала");
        }

        if (campForm.getAgeMin() > campForm.getAgeMax()) {
            validationException.add("ageMax", "Максимальный возраст не может быть меньше минимального");
        }
        validationException.throwIf();
    }

    private void validateCamp (Camp newCamp, BindingResult bindingResult)throws ValidationException{
        ValidationException validationException = new ValidationException();



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
    }


    @Transactional
    public Camp add(CampForm campForm, BindingResult bindingResult) throws ValidationException {
        validateCamp(campForm, bindingResult);
        Camp newCamp = convertToCamp(campForm);

        newCamp.setUser(userService.getCurrentUser());
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

    public Camp put(Long id, CampForm editedCamp, BindingResult bindingResult) throws ObjectNotFoundException, ValidationException {
        validateCamp(editedCamp, bindingResult);
        if (!campRepository.existsByIdAndRemovedFalse(id)){
            throw new ObjectNotFoundException(id, "Camp");
        }
        Camp camp = campRepository.getByIdAndRemovedFalse(id);
        camp.setName(editedCamp.getName());
        camp.setAgeMin(editedCamp.getAgeMin());
        camp.setAgeMax(editedCamp.getAgeMax());
        camp.setDateFinish(editedCamp.getDateFinish());
        camp.setDateStart(editedCamp.getDateStart());
        camp.setInfo(editedCamp.getInfo());
        camp.setChildrenCount(editedCamp.getChildrenCount());
        camp.setPrice(editedCamp.getPrice());
        if (!(editedCamp.getIcon() == null)){
            resizeAndSetIcon(camp, editedCamp.getIcon());
        }
        Place curPlace = new Place();
        curPlace.setId(editedCamp.getPlaceId());
        camp.setPlace(curPlace);

        CampType curCampType = new CampType();
        curCampType.setId(editedCamp.getTypeId());
        camp.setType(curCampType);


        campRepository.save(camp);
        return camp;
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

        List<Predicate> predicateList = createPredicateList(cb, root, campFilter);
        query.where(predicateList.toArray(new Predicate[predicateList.size()]));
        return em.createQuery(query).getResultList();
    }

    public CampPage getManyOnPage(CampFilter campFilter, Long page) {

        CampPage result = new CampPage();
        result.setPage(page);

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Camp> query = cb.createQuery(Camp.class);
        Root<Camp> root = query.from(Camp.class);
        query.select(root);

        Long campCount = getCount(campFilter);
        long pageCount = (long) Math.ceil(campCount / defaultPageSize);
        result.setPageCount(pageCount);
        result.setCampCount(campCount);
        if (page < 1 || page > Math.ceil(campCount / defaultPageSize)) {
            result.setCampList(new ArrayList<>());
            return result;
        }

        List<Predicate> predicateList = createPredicateList(cb, root, campFilter);
        query.where(predicateList.toArray(new Predicate[predicateList.size()]));
        TypedQuery<Camp> tQuery = em.createQuery(query);
        tQuery.setFirstResult((int) ((page - 1) * defaultPageSize));
        tQuery.setMaxResults((int) defaultPageSize);
        result.setCampList(tQuery.getResultList());
        return result;

    }

    private List<Predicate> createPredicateList(CriteriaBuilder cb, Root<Camp> root, CampFilter campFilter) {
        List<Predicate> predicateList = new ArrayList<>();
        predicateList.add(cb.notEqual(root.get("removed"), true));

        if (!(campFilter.getName() == null || campFilter.getName().isEmpty())) {
            predicateList.add(cb.like(root.get("name"), "%" + campFilter.getName() + "%"));
        }
        if (!(campFilter.getAge() == null)) {
            predicateList.add(cb.le(root.get("ageMin"), campFilter.getAge()));
            predicateList.add(cb.ge(root.get("ageMax"), campFilter.getAge()));
        }
        if (!(campFilter.getDateStart() == null || campFilter.getDateStart().isEmpty())) {
            predicateList.add(cb.greaterThanOrEqualTo(root.get("dateStart"), campFilter.getDateStartAsLocalDate()));
        }
        if (!(campFilter.getDateFinish() == null || campFilter.getDateFinish().isEmpty())) {
            predicateList.add(cb.lessThanOrEqualTo(root.get("dateFinish"), campFilter.getDateFinishAsLocalDate()));
        }
        if (!(campFilter.getPlace() == null || campFilter.getPlace().isEmpty())) {
            predicateList.add(cb.equal(root.get("place").get("name"), campFilter.getPlace()));
        }
        if (!(campFilter.getType() == null || campFilter.getType().isEmpty())) {
            predicateList.add(cb.equal(root.get("type").get("name"), campFilter.getType()));
        }
        if (!(campFilter.getPriceMin() == null)) {
            predicateList.add(cb.ge(root.get("price"), campFilter.getPriceMin()));
        }
        if (!(campFilter.getPriceMax() == null)) {
            predicateList.add(cb.le(root.get("price"), campFilter.getPriceMax()));
        }
        return predicateList;
    }

    private Long getCount(CampFilter campFilter) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Camp> root = query.from(Camp.class);
        List<Predicate> predicateList = createPredicateList(cb, root, campFilter);
        query.select(cb.count(root));
        query.where(predicateList.toArray(new Predicate[predicateList.size()]));
        return em.createQuery(query).getSingleResult();
    }

    public Long getAllCount() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Camp> root = query.from(Camp.class);
        query.select(cb.count(root));
        query.where(cb.notEqual(root.get("removed"), true));
        return em.createQuery(query).getSingleResult();
    }

}
