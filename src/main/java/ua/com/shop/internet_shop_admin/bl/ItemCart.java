package ua.com.shop.internet_shop_admin.bl;

import lombok.*;
import ua.com.shop.internet_shop_admin.entity.Product;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class ItemCart {
  private Product product;
  private int quantity;

}
