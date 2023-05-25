package ua.com.shop.internet_shop_admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.com.shop.internet_shop_admin.entity.Category;
import ua.com.shop.internet_shop_admin.entity.Product;
import ua.com.shop.internet_shop_admin.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductManagerService {

  private final ProductRepository productRepository;

  @Autowired
  public ProductManagerService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public void saveNewProductToDB(String name, String description, String image, BigDecimal price, Category category){

    Product product = new Product();
    product.setName(name);
    product.setDescription(description);
    product.setImage(image);
    product.setPrice(price);
    product.setCategories(category);

    productRepository.save(product);
  }

  public void updateProduct(Long id, String name, String description, String image, BigDecimal price, Category category){
    Product product = new Product();
    product.setId(id);
    product.setName(name);
    product.setDescription(description);
    product.setImage(image);
    product.setPrice(price);
    product.setCategories(category);

    productRepository.save(product);
  }


  public void deleteProductById(Long id){
    productRepository.deleteById(id);
  }

  public void deleteAllProduct(){
    productRepository.deleteAll();
  }

  public List<Product> findAllProduct(){
    return productRepository.findAll();
  }

  public Product findProductById(Long id){
    return productRepository.findById(id).get();
  }

  public Product findProductByName(String name){
    return productRepository.findByName(name);
  }

  public boolean sizeProductByCategory(Category category){
    List<Product> products  = productRepository.findAllByCategories(category);
    if(products.size()>0){
      return true;
    } else {
      return false;
    }
  }

  @Cacheable(cacheNames = "prod", key = "#category.getId()")
  public List<Product> getProductsByCategory(Category category){
    return productRepository.findAllByCategories(category);
  }

  @Cacheable(cacheNames = "productsbycategory", key = "#pageable.pageNumber")
  public Page<Product> getPageProductByCategory(Category category, Pageable pageable){
    return productRepository.findAllByCategories(pageable, category);
  }
}