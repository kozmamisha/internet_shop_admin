package ua.com.shop.internet_shop_admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.com.shop.internet_shop_admin.service.CategoryManagerService;

@Controller
public class HomeController {
  private  final CategoryManagerService categoryService;

  @GetMapping("/category")
  public String getPageHome(Model model){

    model.addAttribute("hello", "Hello world!");

    return "home";
  }

  @Autowired
  public HomeController(CategoryManagerService categoryService) {
    this.categoryService = categoryService;
  }

//  @GetMapping("/")
//  public String getHome(){
//    return "index";
//  }

  @GetMapping("/products")
  public String getProductAdmin(){
    return "productAdminPage";
  }

  @GetMapping("/categories")
  public String getCategoryAdmin(){
    return "categoryAdminPage";
  }

  @GetMapping("/orders")
  public String getOrdersAdmin(){
    return "order";
  }

  @GetMapping("/customers")
  public String getCustomersAdmin(){
    return "customer";
  }
}
