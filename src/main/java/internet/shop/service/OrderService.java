package internet.shop.service;

import internet.shop.constant.ORDER_STATUS;
import internet.shop.entity.Order;
import internet.shop.entity.OrderStatus;
import internet.shop.exception.FindByIdException;
import internet.shop.repository.OrderRepository;
import internet.shop.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final UserService userService;

    private final OrderStatusRepository orderStatusRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserService userService,
                        OrderStatusRepository orderStatusRepository){
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.orderStatusRepository = orderStatusRepository;
    }

    private Order findOne(Long id) throws FindByIdException {
        Optional<Order> order = orderRepository.findById(id);
        if (!order.isPresent()) {
            throw new FindByIdException("Order not found");
        }
        Order curOrder = order.get();
        if (curOrder.getStatus().getId().equals(ORDER_STATUS.REMOVED.getValue())){
            throw new FindByIdException("Order was removed");
        }
        return curOrder;
    }

    private void addParamsParser(String params, Order order) {
        for (String param : params.split(",")) {
            String[] current = param.split("=");

            switch (current[0].replaceAll(" ", "")) {

                case "user_id":
                    order.setUser(userService.getOne(Long.parseLong(current[1])));
                    break;
                case "status_id":
                    order.setStatus(orderStatusRepository.findById(Long.parseLong(current[1])).get());
                    break;

            }
        }
    }

    public void add(String params) {
        Order newOrder = new Order();
        addParamsParser(params, newOrder);
        orderRepository.save(newOrder);
    }

    public void deleteOne(Long id) {
        Order orderRemoved = findOne(id);
        OrderStatus removedStatus = orderStatusRepository.findById(ORDER_STATUS.REMOVED.getValue()).get();
        orderRemoved.setStatus(removedStatus);
        orderRepository.save(orderRemoved);
    }

    public void put(Long id, String params){
        Order curOrder=findOne(id);
        addParamsParser(params, curOrder);
        orderRepository.save(curOrder);
    }

    public Order getOne(Long id){
        return findOne(id);
    }



}
