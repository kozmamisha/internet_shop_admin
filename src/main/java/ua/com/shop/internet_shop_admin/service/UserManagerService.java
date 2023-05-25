package ua.com.shop.internet_shop_admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.shop.internet_shop_admin.entity.Customer;
import ua.com.shop.internet_shop_admin.entity.Role;
import ua.com.shop.internet_shop_admin.entity.Users;
import ua.com.shop.internet_shop_admin.repository.CustomerRepository;
import ua.com.shop.internet_shop_admin.repository.RoleRepository;
import ua.com.shop.internet_shop_admin.repository.UserRepository;

import java.util.Collections;
import java.util.List;

@Service
public class UserManagerService implements UserDetailsService {

  private final UserRepository userRepository;
  private final CustomerRepository customerRepository;
  private final RoleRepository roleRepository;

  @Autowired
  public UserManagerService(UserRepository userRepository, CustomerRepository customerRepository, RoleRepository roleRepository) {
    this.userRepository = userRepository;
    this.customerRepository = customerRepository;
    this.roleRepository = roleRepository;
  }


  public boolean getLogicByUser(String username) {

    return (!userRepository.findAllByUsername(username).isEmpty()) ? true : false;
  }


  public Users saveNewUserToDB(Users user){

    user.setRoles(Collections.singleton(new Role(1L, "ROLE_User")));
    user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

    return userRepository.save(user);
  }


  public List<Customer> getCustomerList() {
    return customerRepository.findAll();
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Users user1 = userRepository.findByUsername(username);

    System.out.println(user1);

    if(user1==null){
      throw new UsernameNotFoundException("User not found!!!");
    }

    return user1;
  }
}
