package internet.shop.service;


import internet.shop.entity.OrderCamp;
import internet.shop.exception.FindByIdException;
import internet.shop.repository.OrderCampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderCampService {

    private final OrderCampRepository orderCampRepository;

    private final OrderService orderService;

    private final CampService campService;

    @Autowired
    public OrderCampService(OrderCampRepository orderCampRepository,
                            OrderService orderService, CampService campService){
        this.orderCampRepository = orderCampRepository;
        this.orderService = orderService;
        this.campService = campService;
    }

    private OrderCamp findOne(Long id) throws FindByIdException {
        Optional<OrderCamp> orderCamp = orderCampRepository.findById(id);
        if (!orderCamp.isPresent()) {
            throw new FindByIdException("OrderCamp not found");
        }
        OrderCamp curOrderCamp = orderCamp.get();
        if (curOrderCamp.isRemoved()){
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

    public void put(Long id, String params){
        OrderCamp curOrderCamp=findOne(id);
        addParamsParser(params, curOrderCamp);
        orderCampRepository.save(curOrderCamp);
    }

    public OrderCamp getOne(Long id){
        return findOne(id);
    }

}
