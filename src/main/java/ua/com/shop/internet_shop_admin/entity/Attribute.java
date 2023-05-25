package ua.com.shop.internet_shop_admin.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString


@Entity
@Table(name = "attributes")
public class Attribute {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String description;

  private String unit;

  @OneToMany(mappedBy = "attribute")
  private List<AttributeHasProduct> attributeHasProducts;
}
