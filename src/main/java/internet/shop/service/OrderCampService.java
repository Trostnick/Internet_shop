package internet.shop.service;


import internet.shop.entity.OrderCamp;
import internet.shop.entity.OrderStatus;
import internet.shop.exception.FindByIdException;
import internet.shop.repository.OrderCampRepository;
import internet.shop.repository.OrderStatusRepository;
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

    /*@PersistenceContext
    private EntityManager em;

    private final OrderCampRepository orderCampRepository;

    private final OrderService orderService;

    private final CampService campService;

    private final OrderStatusRepository orderStatusRepository;

    @Autowired
    public OrderCampService(OrderCampRepository orderCampRepository, OrderStatusRepository orderStatusRepository,
                            OrderService orderService, CampService campService) {
        this.orderCampRepository = orderCampRepository;
        this.orderService = orderService;
        this.campService = campService;
        this.orderStatusRepository = orderStatusRepository;
    }

    private OrderCamp findOne(Long id) throws FindByIdException {
        Optional<OrderCamp> orderCamp = orderCampRepository.findById(id);
        if (!orderCamp.isPresent()) {
            throw new FindByIdException("OrderCamp not found");
        }
        OrderCamp curOrderCamp = orderCamp.get();
        if (curOrderCamp.isRemoved()) {
            throw new FindByIdException("OrderCamp was removed");
        }
        return curOrderCamp;
    }

    private void addParamsParser(String params, OrderCamp orderCamp) {
        for (String param : params.split(",")) {
            String[] current = param.split("=");

            switch (current[0].replaceAll(" ", "")) {

                case "order_id":
                    orderCamp.setOrder(orderService.getOne(Long.parseLong(current[1])));
                    break;
                case "camp_id":
                    orderCamp.setCamp(campService.getOne(Long.parseLong(current[1])));
                    break;
                case "count":
                    orderCamp.setCount(Integer.parseInt(current[1]));
                    break;

            }
        }
    }

    public void add(String params) {
        OrderCamp newOrder = new OrderCamp();
        addParamsParser(params, newOrder);
        orderCampRepository.save(newOrder);
    }

    public void deleteOne(Long id) {
        OrderCamp orderCampRemoved = findOne(id);
        orderCampRemoved.setRemoved(true);
        orderCampRepository.save(orderCampRemoved);
    }

    public void put(Long id, String params) {
        OrderCamp curOrderCamp = findOne(id);
        addParamsParser(params, curOrderCamp);
        orderCampRepository.save(curOrderCamp);
    }

    public OrderCamp getOne(Long id) {
        return findOne(id);
    }

    public String getMany(String userLogin, String statusName, String campName) {

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
