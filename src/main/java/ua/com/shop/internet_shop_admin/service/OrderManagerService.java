package ua.com.shop.internet_shop_admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.shop.internet_shop_admin.entity.Order;
import ua.com.shop.internet_shop_admin.repository.OrderRepository;

import java.util.List;

@Service
public class OrderManagerService {

  private final OrderRepository orderRepository;

  @Autowired
  public OrderManagerService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public List<Order> findListOrderByStatus(boolean status){
    return orderRepository.findByStatusOrder(status);
  }

  public List<Order> findAllOrder(){
    return orderRepository.findAll();
  }


  public void updateOrderNewStatus(Order order){

    if(order.isStatusOrder()){
      order.setStatusOrder(false);
    } else {
      order.setStatusOrder(true);
    }

    orderRepository.save(order);
  }


}
