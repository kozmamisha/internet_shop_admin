package ua.com.shop.internet_shop_admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ua.com.shop.internet_shop_admin.entity.Role;
import ua.com.shop.internet_shop_admin.repository.RoleRepository;

@SpringBootApplication
public class Main {

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

  @Bean
  CommandLineRunner run(RoleRepository roleRepository) {
    return args -> {
      if (roleRepository.findAll().isEmpty()) {
        roleRepository.save(new Role(1L, "ROLE_User"));
        roleRepository.save(new Role(2L, "ROLE_Manager"));
        roleRepository.save(new Role(3L, "ROLE_Admin"));
      }
    };
  }
}