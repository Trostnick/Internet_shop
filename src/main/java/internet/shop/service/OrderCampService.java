package internet.shop.service;


import internet.shop.model.entity.Order;
import internet.shop.model.entity.OrderCamp;
import internet.shop.repository.OrderCampRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void deleteAllByOrderId(Long id) {
        List<OrderCamp> orderCampList = orderCampRepository.findAllByOrderIdAndRemovedFalse(id);
        orderCampList.forEach(orderCamp -> {
            orderCamp.setRemoved(true);
        });
        orderCampRepository.saveAll(orderCampList);
    }

    public OrderCamp put(Long id, OrderCamp newOrderCamp) {
        if (!orderCampRepository.existsByIdAndRemovedFalse(id)) {
            throw new ObjectNotFoundException(id, "OrderCamp");
        }
        newOrderCamp.setId(id);
        orderCampRepository.save(newOrderCamp);
        return newOrderCamp;
    }

    public OrderCamp changeCount(Long id, int newCount){
        OrderCamp curOrderCamp = orderCampRepository.getByIdAndRemovedFalse(id);
        curOrderCamp.setCount(newCount);
        orderCampRepository.save(curOrderCamp);
        return curOrderCamp;
    }


    public OrderCamp getOne(Long id) {
        OrderCamp curOrderCamp = orderCampRepository.getByIdAndRemovedFalse(id);
        if (curOrderCamp == null) {
            throw new ObjectNotFoundException(id, "OrderCamp");
        }
        return curOrderCamp;
    }

    public List<OrderCamp> getAllInCart() {
        Order curOrder = orderService.getCurOrder();
        if (curOrder == null) {
            return null;
        }
        List<OrderCamp> orderCampList = orderCampRepository.findAllByOrderIdAndRemovedFalse(curOrder.getId());
        if (orderCampList.isEmpty()){
            orderService.deleteOne(curOrder);
            return null;
        }
        return orderCampList;

    }

}
