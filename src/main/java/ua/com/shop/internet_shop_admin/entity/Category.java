package ua.com.shop.internet_shop_admin.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data

@Entity
@Table(name = "category")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String description;
  private String image;

  @OneToMany(mappedBy = "categories")
  private List<Product> productList;
}
