package ua.com.shop.internet_shop_admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.shop.internet_shop_admin.entity.AttributeHasProduct;

public interface AttributeHasProductRepository extends JpaRepository<AttributeHasProduct, Long> {
}