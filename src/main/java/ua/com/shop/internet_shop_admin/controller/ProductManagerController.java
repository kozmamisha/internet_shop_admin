package ua.com.shop.internet_shop_admin.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.shop.internet_shop_admin.bl.Cart;
import ua.com.shop.internet_shop_admin.entity.Category;
import ua.com.shop.internet_shop_admin.entity.Product;
import ua.com.shop.internet_shop_admin.service.CategoryManagerService;
import ua.com.shop.internet_shop_admin.service.ProductManagerService;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class ProductManagerController {

  private final ProductManagerService productService;

  private final CategoryManagerService categoryManagerService;

  @Autowired
  public ProductManagerController(ProductManagerService productService, CategoryManagerService categoryManagerService) {
    this.productService = productService;
    this.categoryManagerService = categoryManagerService;
  }

  @GetMapping("/category/{id}")
  public String getAllProductByCategory(@PathVariable(name = "id") Category category,
                                        Model model){
    List<Product> productList = productService.getProductsByCategory(category);
    model.addAttribute("allProductByCategory", productList);

    String name = (!productList.isEmpty()) ? category.getName() : " ";
    model.addAttribute("category", name);

    return "productByCategory";
  }

  @GetMapping("/productbycategory/{id}")
  public String getPageProductsByCategory(
          @PathVariable(name = "id") Category category,
          @PageableDefault(sort = {"price"}, direction = Sort.Direction.ASC, size = 4) Pageable pageable,
          Model model){

    model.addAttribute("url", "/productbycategory/"+category.getId());
    model.addAttribute("page", productService.getPageProductByCategory(category, pageable));


    return "productsbycategory";
  }



  @PostMapping("/cart")
  public String addNewItemToCart(@RequestParam(name = "id") Product product,
                                 @RequestParam(name = "quantity") int quantity,
                                 HttpServletRequest request
  ){

    HttpSession session = request.getSession();
    // get/set
    Cart cart = (Cart) session.getAttribute("cart");

    if(cart == null) cart = new Cart();

    cart.addNewItemToCart(product, quantity);

    session.setAttribute("cart", cart);

    return "redirect:/cart";
  }


  @GetMapping("/cart")
  public String getPageCart(HttpServletRequest request,
                            Model model){

    HttpSession session = request.getSession();

    Cart cart = (Cart) session.getAttribute("cart");

    if (cart==null) cart = new Cart();

    model.addAttribute("itemCart", cart.getCart());
    model.addAttribute("totalEl", cart.getSumItemCart());
    model.addAttribute("totalValue", cart.getTotalVal());

    return "cart";
  }

  @PostMapping("/updateItemCart")
  public String updateItemCart(@RequestParam(name = "id") Product product,
                               @RequestParam(name = "quantity") int quantity,
                               HttpServletRequest request
  ){

    HttpSession session = request.getSession();
    Cart cart = (Cart) session.getAttribute("cart");

    if(cart==null) cart = new Cart();

    cart.updateItemCart(product, quantity);

    session.setAttribute("cart", cart);

    return "redirect:/cart";
  }


  @PostMapping("/deleteItemCart")
  public String deleteItemFromCart(@RequestParam(name = "id") Product product,
                                   HttpServletRequest request){

    HttpSession session = request.getSession();
    Cart cart = (Cart) session.getAttribute("cart");

    if(cart==null) cart = new Cart();

    cart.deleteItemFromCart(product);

    session.setAttribute("cart", cart);

    return "redirect:/cart";
  }

  @PostMapping("/deleteItemsCart")
  public String deleteItemsFromCart(HttpServletRequest req){

    HttpSession session = req.getSession();

    Cart cart = (Cart) session.getAttribute("cart");
    if(cart==null) cart = new Cart();

    cart.deleteAllItemFromCart();

    session.setAttribute("cart", cart);

    return "redirect:/cart";
  }

  @GetMapping("/productmanager")
  public String getAllProduct(Model model) {
    model.addAttribute("allProduct", productService.findAllProduct());
    model.addAttribute("allCategory", categoryManagerService.getCategoryList());
    return "productAdminPage";
  }


  @PostMapping("/saveNewProduct")
  public String saveNewProductToDB(@RequestParam(value = "name") String name,
                                   @RequestParam(value = "description") String descr,
                                   @RequestParam(value = "image") String image,
                                   @RequestParam(value = "price") BigDecimal price,
                                   @RequestParam(value = "categoryId") Category category
  ) {

    productService.saveNewProductToDB(name, descr, image, price, category);

    return "redirect:/productmanager";
  }

  @PostMapping("/updateProduct")
  public String updateProduct(
          @RequestParam(value = "id") Long id,
          @RequestParam(value = "name") String name,
          @RequestParam(value = "description") String descr,
          @RequestParam(value = "image") String image,
          @RequestParam(value = "price") String price1,
          @RequestParam(value = "categoryId") Category category
  ) {


    BigDecimal price = new BigDecimal(Double.valueOf(price1.replaceAll("[^0-9]","")));

    productService.updateProduct(id,name, descr, image, price, category);

    return "redirect:/productmanager";
  }


  @PostMapping("/deleteProduct")
  public String deleteProduct(@RequestParam(value = "id") Long id){

    productService.deleteProductById(id);

    return "redirect:/productmanager";
  }


  @PostMapping("/deleteAllProduct")
  public String deleteAllProduct(){

    productService.deleteAllProduct();

    return "redirect:/productmanager";
  }

  @GetMapping("/productmanager/{id}")
  public String getCategoryPageById(@PathVariable("id") Long id,
                                    Model model){

    Product product = productService.findProductById(id);
    model.addAttribute("product", product);

    return "productDet";
  }


}