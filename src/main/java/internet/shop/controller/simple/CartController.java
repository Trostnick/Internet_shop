package internet.shop.controller.simple;

import internet.shop.model.entity.Order;
import internet.shop.model.entity.OrderPart;
import internet.shop.service.CampService;
import internet.shop.service.OrderPartService;
import internet.shop.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class CartController {

    private final OrderService orderService;

    private final OrderPartService orderPartService;

    private final CampService campService;

    public CartController(OrderService orderService, OrderPartService orderPartService,
                          CampService campService) {
        this.orderPartService = orderPartService;
        this.orderService = orderService;
        this.campService = campService;
    }

    @GetMapping("/orders")
    public ModelAndView getAllOrdersPage() {
        ModelAndView modelAndView = new ModelAndView("allUserOrders");
        Map<String, Object> model = modelAndView.getModel();
        List<Order> orderList = orderService.getAll();
        if (orderList.isEmpty()) {
            model.put("orderList", null);
        } else {
            model.put("orderList", orderList);
        }
        return modelAndView;

    }

    @GetMapping("/cart")
    public ModelAndView getCartPage() {
        ModelAndView modelAndView = new ModelAndView("cart");
        Map<String, Object> model = modelAndView.getModel();

        List<OrderPart> orderPartList = orderPartService.getAllInCart();

        model.put("orderPartList", orderPartList);
        if (orderPartList == null) {
            model.put("orderPrice", null);
        } else {
            model.put("orderPrice", orderPartService.getOrderPrice(orderPartList));
        }

        return modelAndView;
    }

    @GetMapping("/cart/{id}")
    public ModelAndView getAddToCartPage(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("addToCart");
        Map<String, Object> model = modelAndView.getModel();
        model.put("camp", campService.getOne(id));
        return modelAndView;
    }

    @GetMapping("/cart/remove")
    public ModelAndView removeCart() {
        Order curOrder = orderService.getCurOrder();
        orderPartService.deleteAllByOrderId(curOrder.getId());
        orderService.deleteOne(curOrder);
        return new ModelAndView("redirect:/cart");
    }

    @GetMapping("/cart/confirm")
    public ModelAndView confirmOrderPage() {
        ModelAndView modelAndView = new ModelAndView("confirmOrder");
        Map<String, Object> model = modelAndView.getModel();

        List<OrderPart> orderPartList = orderPartService.getAllInCart();

        model.put("orderPartList", orderPartList);
        if (orderPartList == null) {
            model.put("orderPrice", null);
        } else {
            model.put("orderPrice", orderPartService.getOrderPrice(orderPartList));
        }
        return modelAndView;
    }

    @PostMapping("/cart/confirm")
    public ModelAndView confirm() {

        orderService.confirm();
        return new ModelAndView("redirect:/home");
    }

}
