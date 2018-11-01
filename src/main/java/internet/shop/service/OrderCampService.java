package internet.shop.service;


import internet.shop.entity.OrderCamp;
import internet.shop.entity.OrderStatus;
import internet.shop.exception.FindByIdException;
import internet.shop.repository.OrderCampRepository;
import internet.shop.repository.OrderRepository;
import internet.shop.repository.OrderStatusRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Service
public class OrderCampService {

    @PersistenceContext
    private EntityManager em;

    private final OrderCampRepository orderCampRepository;

    private final OrderRepository orderRepository;

    private final CampService campService;

    private final OrderStatusRepository orderStatusRepository;

    @Autowired
    public OrderCampService(OrderCampRepository orderCampRepository, OrderStatusRepository orderStatusRepository,
                            OrderRepository orderRepository, CampService campService) {
        this.orderCampRepository = orderCampRepository;
        this.orderRepository = orderRepository;
        this.campService = campService;
        this.orderStatusRepository = orderStatusRepository;
    }


    public OrderCamp add(OrderCamp newOrderCamp) {
        orderCampRepository.save(newOrderCamp);
        return newOrderCamp;
    }

    public void deleteOne(Long id) throws ObjectNotFoundException {
        OrderCamp orderCampRemoved = orderCampRepository.getByIdAndRemovedFalse(id);
        if (orderCampRemoved == null) {
            throw new ObjectNotFoundException(id, "OrderCamp");
        }
        orderCampRemoved.setRemoved(true);
        orderCampRepository.save(orderCampRemoved);
    }

    public OrderCamp put(Long id, OrderCamp newOrderCamp) {
        if (!orderCampRepository.existsByIdAndRemovedFalse(id)) {
            throw new ObjectNotFoundException(id, "OrderCamp");
        }
        newOrderCamp.setId(id);
        orderCampRepository.save(newOrderCamp);
        return newOrderCamp;
    }

    public OrderCamp getOne(Long id) {
        OrderCamp curOrderCamp = orderCampRepository.getByIdAndRemovedFalse(id);
        if (curOrderCamp == null) throw new ObjectNotFoundException(id, "OrderCamp");
        return curOrderCamp;
    }

    /*public String getMany(String userLogin, String statusName, String campName) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<OrderCamp> query = cb.createQuery(OrderCamp.class);
        Root<OrderCamp> root = query.from(OrderCamp.class);
        query.select(root);

        Predicate criteriaLogin = cb.equal(root.get("order").get("user").get("login"), userLogin);

        list

        if (statusName.equals("all") && campName.equals("all")) {
            OrderStatus removedType = orderStatusRepository.findById(ORDER_STATUS.REMOVED.getValue()).get();
            Predicate criteriaRemoved = cb.notEqual(root.get("order").get("status"), removedType);
            query.where(cb.and(criteriaLogin, criteriaRemoved));
        } else if (!statusName.equals("all") && campName.equals("all")) {
            Predicate criteriaStatus = cb.equal(root.get("order").get("status").get("name"), statusName);
            query.where(cb.and(criteriaLogin, criteriaStatus));
        } else if (statusName.equals("all") && !campName.equals("all")) {
            OrderStatus removedType = orderStatusRepository.findById(ORDER_STATUS.REMOVED.getValue()).get();
            Predicate criteriaRemoved = cb.notEqual(root.get("order").get("status"), removedType);
            Predicate criteriaCamp = cb.equal(root.get("camp").get("name"), campName);
            query.where(cb.and(criteriaLogin, criteriaRemoved, criteriaCamp));
        } else {
            Predicate criteriaStatus = cb.equal(root.get("order").get("status").get("name"), statusName);
            Predicate criteriaCamp = cb.equal(root.get("camp").get("name"), campName);
            query.where(cb.and(criteriaLogin, criteriaStatus, criteriaCamp));
        }


        List<OrderCamp> orders = em.createQuery(query).getResultList();

        StringBuilder result = new StringBuilder();
        for (OrderCamp order : orders) {
            result.append(order.toString());
        }
        return result.toString();
    }*/


}
