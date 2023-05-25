package ua.com.shop.internet_shop_admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.shop.internet_shop_admin.entity.Customer;
import ua.com.shop.internet_shop_admin.entity.Users;
import ua.com.shop.internet_shop_admin.repository.CustomerRepository;

@Service
public class CustomerManagerService {
  private final CustomerRepository customerRepository;

  @Autowired
  public CustomerManagerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public void saveCustomerToDB(Customer customer){
    customerRepository.save(customer);
  }

  public Customer getClientByUser(Users users){
    return customerRepository.findByUser(users);
  }


}