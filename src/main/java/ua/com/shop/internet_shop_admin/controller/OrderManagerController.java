package ua.com.shop.internet_shop_admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.shop.internet_shop_admin.entity.Order;
import ua.com.shop.internet_shop_admin.service.OrderManagerService;

@Service
public class OrderManagerController {


  private final OrderManagerService orderService;

  @Autowired
  public OrderManagerController(OrderManagerService orderService) {
    this.orderService = orderService;
  }


  @GetMapping("/ordermanager")
  public String getAllOrderPage(Model model){
    model.addAttribute("order1", orderService.findListOrderByStatus(false));
    model.addAttribute("order2", orderService.findListOrderByStatus(true));
    return "orderAdmin";
  }

  @PostMapping("/updateOrder")
  public String updateOrder(@RequestParam(value = "id") Order order){
    orderService.updateOrderNewStatus(order);

    return "redirect:/ordermanager";
  }
}
