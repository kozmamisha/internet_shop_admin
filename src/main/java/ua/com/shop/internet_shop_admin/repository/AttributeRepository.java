package ua.com.shop.internet_shop_admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.shop.internet_shop_admin.entity.Attribute;

public interface AttributeRepository extends JpaRepository<Attribute,Long> {
}
