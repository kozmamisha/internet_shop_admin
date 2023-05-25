package ua.com.shop.internet_shop_admin.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Date dateCreated;
  private String payment;
  private String delivery;
  private boolean statusOrder;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @OneToMany(mappedBy = "order")
  private List<ProductHasOrder> productsHasOrder;

}