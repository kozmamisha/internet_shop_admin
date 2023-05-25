package ua.com.shop.internet_shop_admin.controller;

import com.mysql.cj.xdevapi.Client;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.shop.internet_shop_admin.bl.Cart;
import ua.com.shop.internet_shop_admin.bl.ItemCart;
import ua.com.shop.internet_shop_admin.entity.*;
import ua.com.shop.internet_shop_admin.repository.AddressRepository;
import ua.com.shop.internet_shop_admin.repository.OrderRepository;
import ua.com.shop.internet_shop_admin.repository.ProductHasOrderRepository;
import ua.com.shop.internet_shop_admin.service.CustomerManagerService;
import ua.com.shop.internet_shop_admin.service.UserManagerService;

import java.util.Date;
import java.util.List;

@Controller
public class UserManagerController {

  private final CustomerManagerService customerService;
  private final UserManagerService userService;

  private final AddressRepository addressRepository;

  private final OrderRepository orderRepository;

  private final ProductHasOrderRepository productHasOrderRepository;


  @Autowired
  public UserManagerController(CustomerManagerService customerService, UserManagerService userService, AddressRepository addressRepository, OrderRepository orderRepository, ProductHasOrderRepository productHasOrderRepository) {
    this.customerService = customerService;
    this.userService = userService;
    this.addressRepository = addressRepository;
    this.orderRepository = orderRepository;
    this.productHasOrderRepository = productHasOrderRepository;
  }

  @GetMapping("/login")
  public String getLoginPage(){
    return "login";
  }


  @GetMapping("/registration")
  public String getRegistrationPage(Model model){

    model.addAttribute("users", new Users());
    model.addAttribute("customer", new Customer());

    return "registration";
  }


  @PostMapping("/registration")
  public String saveNewCustomer(@Valid Users users,
                                BindingResult bindingResult,
                                @Valid Customer customer,
                                BindingResult bindingResult1,
                                Model model
  ){

    if(bindingResult.hasErrors()){
      return "/registration";
    }

    if(bindingResult1.hasErrors()){
      return "/registration";
    }

    if(userService.getLogicByUser(users.getUsername())){
      return "/registration";
    }


    Users user1 = userService.saveNewUserToDB(users);

    customer.setUser(user1);

    customerService.saveCustomerToDB(customer);

    return "redirect:/login";
  }

  @GetMapping("/customermanager")
  public String getCustomerList(Model model){

    model.addAttribute("customers",  userService.getCustomerList());

    return "customerAdmin";
  }

  @GetMapping("/order")
  public String getPageOrder(HttpServletRequest request, Model model){


    HttpSession session = request.getSession();

    Users user = (Users) session.getAttribute("user");

//    if(user == null) {
//      return "redirect:/";
//    }

    Cart cart = (Cart) session.getAttribute("cart");

    if (cart==null) return "redirect:/";

    model.addAttribute("cart", cart.getCart());
    model.addAttribute("sumItem", cart.getSumItemCart());
    model.addAttribute("totalValue", cart.getTotalVal());


    Customer client = customerService.getClientByUser(user);

    List<Address> addresses = addressRepository.findAllByClientes(client);




    model.addAttribute("client", client);

    model.addAttribute("addresses", addresses);


    return "order";
  }

  @PostMapping("/order")
  public String saveOrderToDB(@RequestParam(name = "delivery") String  delivery,
                              @RequestParam(name = "payment") String payment,
                              @RequestParam(name = "address") Address address,
                              HttpServletRequest request
  ){

    HttpSession session = request.getSession();

    Cart cart  = (Cart) session.getAttribute("cart");
    Users user = (Users) session.getAttribute("user");

    Customer client = customerService.getClientByUser(user);


    Order order = new Order();
    order.setDateCreated(new Date());
    order.setCustomer(client);
    order.setDelivery(delivery);
    order.setPayment(payment);
    order.setStatusOrder(false);


    Order order1 = orderRepository.save(order);


    for (ItemCart el : cart.getCart()) {
      productHasOrderRepository.save(new ProductHasOrder(el.getProduct(), el.getQuantity(), order1));
    }

    return "thank";
  }


  @GetMapping("/thank")
  public String getPageThank(){
    return "thank";
  }



}