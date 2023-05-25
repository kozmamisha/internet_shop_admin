package ua.com.shop.internet_shop_admin.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data

@Entity
@Table(name = "products")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String description;
  private BigDecimal price;
  private String image;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category categories;

  @OneToMany(mappedBy = "product")
  private List<ProductHasOrder> productHasOrders;

  @OneToMany(mappedBy = "productes")
  private List<AttributeHasProduct> attributeHasProductList;


}