package ua.com.shop.internet_shop_admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.shop.internet_shop_admin.entity.Users;

import java.util.List;

public interface UserRepository extends JpaRepository<Users, Long> {

  List<Users> findAllByUsername(String name);

  Users findByUsername(String name);

  List<Users> findAllByUsernameAndPassword(String username, String pass);

  Users findByUsernameAndPassword(String user, String pass);

}
