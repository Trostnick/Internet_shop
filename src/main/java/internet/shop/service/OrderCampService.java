package internet.shop.service;


import internet.shop.entity.Order;
import internet.shop.entity.OrderCamp;
import internet.shop.repository.OrderCampRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderCampService {


    private final OrderCampRepository orderCampRepository;

    private final OrderService orderService;

    @Autowired
    public OrderCampService(OrderCampRepository orderCampRepository,
                            OrderService orderService) {
        this.orderCampRepository = orderCampRepository;
        this.orderService = orderService;
    }


    public OrderCamp add(OrderCamp newOrderCamp) {
        Order curOrder = orderService.getCurOrderOrCreate();
        newOrderCamp.setOrder(curOrder);
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

    public List<OrderCamp> getAllInBasket() {
        Order curOrder = orderService.getCurOrder();
        if (curOrder == null) {
            return new ArrayList<>();
        }
        return orderCampRepository.findAllByOrderIdAndRemovedFalse(curOrder.getId());

    }

}
