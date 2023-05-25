package ua.com.shop.internet_shop_admin.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

@Entity
@Table(name = "product_has_order")
public class ProductHasOrder {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order order;

  private int quantity;

  public ProductHasOrder(Product product, int quantity, Order order) {
    this.product = product;
    this.order = order;
    this.quantity = quantity;
  }

}
