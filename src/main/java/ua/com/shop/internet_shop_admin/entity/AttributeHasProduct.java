package ua.com.shop.internet_shop_admin.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "attribute_has_product")
public class AttributeHasProduct {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "attribute_id")
  private Attribute attribute;

  @ManyToOne
  @JoinColumn(name = "productes_id")
  private Product productes;
}