package internet.shop.controller;

import internet.shop.entity.OrderCamp;
import internet.shop.repository.UserRepository;
import internet.shop.service.CampService;
import internet.shop.service.OrderCampService;
import internet.shop.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller

public class BasketController {

    private final OrderService orderService;

    private final OrderCampService orderCampService;

    private final CampService campService;

    public BasketController(OrderService orderService, OrderCampService orderCampService,
                            CampService campService) {
        this.orderCampService = orderCampService;
        this.orderService = orderService;
        this.campService = campService;
    }

    @GetMapping("/orders")
    public ModelAndView getAllOrdersPage() {
        ModelAndView modelAndView = new ModelAndView("allUserOrders");
        Map<String, Object> model = modelAndView.getModel();


        model.put("orders", orderService.getAll());

        return modelAndView;

    }

    @GetMapping("/basket")
    public ModelAndView getBasketPage() {
        ModelAndView modelAndView = new ModelAndView("basket");
        Map<String, Object> model = modelAndView.getModel();

        List<OrderCamp> orderCampList = orderCampService.getAllInBasket();

        if (orderCampList.isEmpty()) {
            model.put("orderCampList", null);
        } else {
            int orderPrice = 0;
            model.put("orderCampList", orderCampList);

            for (OrderCamp orderCamp : orderCampList) {
                orderPrice += orderCamp.getCamp().getPrice() * orderCamp.getCount();
            }
            model.put("orderPrice", orderPrice);
        }

        return modelAndView;
    }

    @GetMapping("/basket/{id}")
    public ModelAndView getAddToBasketPage(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("addToBasket");
        Map<String, Object> model = modelAndView.getModel();
        model.put("camp", campService.getOne(id));
        return modelAndView;
    }

    @GetMapping("/basket/remove")
    public ModelAndView removeBasket(){
        orderService.deleteOne(orderService.getCurOrder());
        return new ModelAndView("redirect:/home");
    }

}