package ua.com.shop.internet_shop_admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.shop.internet_shop_admin.entity.Address;
import ua.com.shop.internet_shop_admin.entity.Customer;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

  List<Address> findAllByClientes(Customer client);
}