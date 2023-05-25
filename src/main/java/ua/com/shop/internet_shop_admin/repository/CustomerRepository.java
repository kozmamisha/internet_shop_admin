package ua.com.shop.internet_shop_admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.shop.internet_shop_admin.entity.Customer;
import ua.com.shop.internet_shop_admin.entity.Users;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

  Customer findByUser(Users users);

}