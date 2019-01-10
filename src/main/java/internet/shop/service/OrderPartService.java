package internet.shop.service;


import internet.shop.model.entity.Order;
import internet.shop.model.entity.OrderPart;
import internet.shop.repository.OrderCampRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class OrderPartService {


    private final OrderCampRepository orderCampRepository;

    private final OrderService orderService;

    @Autowired
    public OrderPartService(OrderCampRepository orderCampRepository,
                            OrderService orderService) {
        this.orderCampRepository = orderCampRepository;
        this.orderService = orderService;
    }


    public OrderPart add(OrderPart newOrderPart) {
        Order curOrder = orderService.getCurOrderOrCreate();
        newOrderPart.setOrder(curOrder);
        orderCampRepository.save(newOrderPart);
        return newOrderPart;
    }

    public void deleteOne(Long id) throws ObjectNotFoundException {
        OrderPart orderPartRemoved = orderCampRepository.getByIdAndRemovedFalse(id);
        if (orderPartRemoved == null) {
            throw new ObjectNotFoundException(id, "OrderPart");
        }
        orderPartRemoved.setRemoved(true);
        orderCampRepository.save(orderPartRemoved);
    }

    public void deleteAllByOrderId(Long id) {
        List<OrderPart> orderPartList = orderCampRepository.findAllByOrderIdAndRemovedFalse(id);
        orderPartList.forEach(orderPart -> orderPart.setRemoved(true));
        orderCampRepository.saveAll(orderPartList);
    }

    public OrderPart put(Long id, OrderPart newOrderPart) {
        if (!orderCampRepository.existsByIdAndRemovedFalse(id)) {
            throw new ObjectNotFoundException(id, "OrderPart");
        }
        newOrderPart.setId(id);
        orderCampRepository.save(newOrderPart);
        return newOrderPart;
    }

    public OrderPart changeCount(Long id, int newCount){
        OrderPart curOrderPart = orderCampRepository.getByIdAndRemovedFalse(id);
        curOrderPart.setCount(newCount);
        orderCampRepository.save(curOrderPart);
        return curOrderPart;
    }


    public OrderPart getOne(Long id) {
        OrderPart curOrderPart = orderCampRepository.getByIdAndRemovedFalse(id);
        if (curOrderPart == null) {
            throw new ObjectNotFoundException(id, "OrderPart");
        }
        return curOrderPart;
    }

    public List<OrderPart> getAllInCart() {
        Order curOrder = orderService.getCurOrder();
        if (curOrder == null) {
            return null;
        }
        List<OrderPart> orderPartList = orderCampRepository.findAllByOrderIdAndRemovedFalse(curOrder.getId());
        if (orderPartList.isEmpty()){
            orderService.deleteOne(curOrder);
            return null;
        }
        return orderPartList;

    }

    public BigInteger getOrderPrice(List<OrderPart> orderPartList){
        BigInteger sum = new BigInteger("0");
        for (OrderPart orderPart : orderPartList){
            BigInteger count = new BigInteger(((Integer)orderPart.getCount()).toString());
            BigInteger price = new BigInteger(((Integer)orderPart.getCamp().getPrice()).toString());
            sum = sum.add(price.multiply(count));
        }
        return sum;

    }

}
