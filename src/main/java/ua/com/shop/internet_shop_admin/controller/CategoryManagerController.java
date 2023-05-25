package ua.com.shop.internet_shop_admin.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.com.shop.internet_shop_admin.entity.Category;
import ua.com.shop.internet_shop_admin.service.CategoryManagerService;
import ua.com.shop.internet_shop_admin.service.ProductManagerService;

import java.util.List;

@Controller
public class CategoryManagerController {

  private final CategoryManagerService categoryService;
  private final ProductManagerService productService;

  @Autowired
  public CategoryManagerController(CategoryManagerService categoryService, ProductManagerService productService) {
    this.categoryService = categoryService;
    this.productService = productService;
  }

  @GetMapping("/")
  public String getPageCategory(Model model){

    List<Category> categoryList = categoryService.getAllCategory();
    model.addAttribute("allCategory", categoryList);

    return "category";
  }

  @GetMapping("/categorypage")
  public String getPageCategory(Model model,
                                @PageableDefault(sort = {"id"},
                                        direction = Sort.Direction.ASC,
                                        size = 2) Pageable pageable){
    model.addAttribute("url","/categorypage");
    model.addAttribute("page",categoryService.getAllPageCategory(pageable));

    return "categorypage";
  }


  @GetMapping("/categorymanager")
  public String getCategoryManagerPage(Model model,
                                       @RequestParam(name = "Err", defaultValue = "" ) String name,
                                       @RequestParam(name = "Error", defaultValue = "" ) String name1
  ){
    model.addAttribute("allCategory", categoryService.getCategoryList());
    model.addAttribute("Err",name);
    model.addAttribute("Error",name1);

    return "categoryAdminPage";
  }

  @PostMapping("/saveNewCategory")
  public String  saveNewCategoryToDB(@RequestParam(value = "name") String  name,
                                     @RequestParam(value = "description") String  descr,
                                     @RequestParam(value = "image") String image
  )
  {
    categoryService.saveCategory(name, descr, image);
    return "redirect:/categorymanager";
  }


  @PostMapping("/updateCategory")
  public String  updateCategory(@RequestParam(value = "id") Long id,
                                @RequestParam(value = "name") String  name,
                                @RequestParam(value = "description") String  descr,
                                @RequestParam(value = "image") String image)
  {
    categoryService.updateCategory(id, name, descr, image);
    return "redirect:/categorymanager";
  }


  @PostMapping("/deleteCategory")
  public String deletecategory(@RequestParam(value = "id") Long id,
                               @RequestParam(value = "id") Category category,
                               RedirectAttributes redirectAttributes){


    if(!productService.sizeProductByCategory(category)){
      categoryService.deleteCategory(id);
      return "redirect:/categorymanager";
    } else {
      redirectAttributes.addAttribute("Err", "Перед видаленням категорії,будь ласка, видаліть продукцію з категорії");
      return "redirect:/categorymanager";
    }
  }


  @PostMapping("/deleteAllCategory")
  public String deleteAllCategory(RedirectAttributes redirectAttributes){

    if(productService.findAllProduct().size()==0){
      categoryService.deleteAllCategory();
      return "redirect:/categorymanager";
    } else {
      redirectAttributes.addAttribute("Error", "Перед видаленням категорій, будь ласка, видаліть всю продукцію з категорій");
      return "redirect:/categorymanager";
    }
  }


  @GetMapping("/categorymanager/{id}")
  public String getCategoryById(@PathVariable("id") Long id,
                                Model model){
    Category category = categoryService.getCategoryById(id);

    model.addAttribute("category", category);

    return "categoryById";
  }




}
