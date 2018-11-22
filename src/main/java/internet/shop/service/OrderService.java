package internet.shop.service;

import internet.shop.model.entity.Order;
import internet.shop.model.entity.OrderStatus;
import internet.shop.model.entity.User;
import internet.shop.repository.OrderRepository;
import internet.shop.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class OrderService {


    private final OrderRepository orderRepository;

    private final UserService userService;

    private final OrderStatusRepository orderStatusRepository;


    @Autowired
    public OrderService(OrderRepository orderRepository, UserService userService,
                        OrderStatusRepository orderStatusRepository) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.orderStatusRepository = orderStatusRepository;
    }


    public Order add(Order newOrder) {

        orderRepository.save(newOrder);
        return newOrder;
    }

    private Order add(User user) {
        Order newOrder = new Order();
        newOrder.setUser(user);
        newOrder.setStatus(orderStatusRepository.getByName("in cart"));
        orderRepository.save(newOrder);
        return newOrder;
    }

    public Order deleteOne(Order orderRemoved) {
        orderRemoved.setRemoved(true);
        orderRepository.save(orderRemoved);
        return orderRemoved;
    }

    public Order getCurOrderOrCreate() {
        User user = userService.getCurrentUser();
        Optional<Order> curOrder = orderRepository.findByStatusNameAndUserLoginAndRemovedFalse("in cart", user.getLogin());
        return curOrder.orElseGet(() -> add(user));
    }

    public Order getCurOrder() {
        User user = userService.getCurrentUser();
        Optional<Order> curOrder = orderRepository.findByStatusNameAndUserLoginAndRemovedFalse("in cart", user.getLogin());
        return curOrder.orElseGet(() -> null);
    }


    public void confirm(){
        Order curOrder = getCurOrder();
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setId(2L);
        curOrder.setStatus(orderStatus);
        orderRepository.save(curOrder);
    }

    public List<Order> getAll() {
        User user = userService.getCurrentUser();
        return orderRepository.findAllByUserIdAndRemovedFalse(user.getId());
    }


}
