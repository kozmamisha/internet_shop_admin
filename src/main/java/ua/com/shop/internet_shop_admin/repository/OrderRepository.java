package ua.com.shop.internet_shop_admin.repository;

import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.shop.internet_shop_admin.entity.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
  List<Order> findByStatusOrder(boolean status);
}