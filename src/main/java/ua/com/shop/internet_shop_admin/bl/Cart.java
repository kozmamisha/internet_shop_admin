package ua.com.shop.internet_shop_admin.bl;

import lombok.Getter;
import lombok.Setter;
import ua.com.shop.internet_shop_admin.entity.Product;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class Cart {

  List<ItemCart> cart;
  private double totalValue;
  private int sumItem;


  public Cart() {
    cart = new ArrayList<>();
    totalValue = 0;
    sumItem = 0;
  }

  public synchronized void addNewItemToCart(Product product, int quantity){

    boolean logic = true;

    for (ItemCart el : cart) {
      if(product.getId()==el.getProduct().getId()){
        logic = false;
        el.setQuantity(el.getQuantity() + quantity);
      }
    }

    if(logic) cart.add(new ItemCart(product, quantity));
  }

  public synchronized void updateItemCart(Product product, int quantity){
    if(quantity <= 0){
      for (ItemCart el : cart) {
        if(el.getProduct().getId() == product.getId()){
          cart.remove(el);
          break;
        }
      }
    } else {
      for (ItemCart el : cart) {
        if(product.getId() == el.getProduct().getId())
          el.setQuantity(quantity);
      }
    }
  }


  public synchronized void deleteItemFromCart(Product product){
    for (ItemCart el : cart) {
      if(product.getId()==el.getProduct().getId()){
        cart.remove(el);
        break;
      }
    }
  }

  public synchronized void deleteAllItemFromCart(){
    cart.clear();
    totalValue = 0;
    sumItem = 0;
  }

  public synchronized double getTotalVal(){

    totalValue = 0;
    for (ItemCart el : cart) {
      totalValue += el.getQuantity()*el.getProduct().getPrice().doubleValue();
    }
    return totalValue;
  }


  public  synchronized int getSumItemCart(){
    int a = 0;
    for (ItemCart el : cart) {
      a++;
    }
    return a;
  }


}