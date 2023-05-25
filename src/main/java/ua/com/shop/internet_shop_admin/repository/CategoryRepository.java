package ua.com.shop.internet_shop_admin.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.shop.internet_shop_admin.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

  Category findByName(String name);

  Page<Category> findAll(Pageable pageable);
}
