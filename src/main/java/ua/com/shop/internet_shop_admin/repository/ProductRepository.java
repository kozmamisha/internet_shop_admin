package ua.com.shop.internet_shop_admin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.shop.internet_shop_admin.entity.Category;
import ua.com.shop.internet_shop_admin.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  Product findByName(String name);
  List<Product> findAllByCategories(Category category);

  Page<Product> findAllByCategories(Pageable pageable, Category category);
}