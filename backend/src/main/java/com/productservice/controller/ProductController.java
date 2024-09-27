package com.productservice.controller;

import com.productservice.dto.ProductDto;
import com.productservice.model.Product;
import com.productservice.model.Review;
import com.productservice.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/products/")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    private static final Logger LOGGER = Logger.getLogger(ProductController.class.getName());

    @Autowired
    ProductService productService;


    // Endpoint to create a new product
    @PostMapping
    public ProductDto createProduct(@ModelAttribute ProductDto productDto) throws IOException {
        LOGGER.log(Level.INFO, "POST request received to create a new product: {0}", productDto.getName());
        System.out.println(productDto);
        return productService.createProduct(productDto);
    }

    // Endpoint to get All products
   @GetMapping
   public List<Product> getAllProduct(){
       return productService.getAllProducts();
   }



    // Endpoint to get a specific product by ID
    @GetMapping("{id}")
    public Product getProductById(@PathVariable Long id) {
        LOGGER.log(Level.INFO, "GET request received for product with ID: {0}", id);
        return productService.getProductById(id);
    }

    // Endpoint to search for products
    @GetMapping("search")
    public List<ProductDto> searchProducts(@RequestParam(value = "name", required = false) String name,
                                        @RequestParam(value = "brand", required = false) String brand,
                                        @RequestParam(value = "price",required = false) Long price) {
        LOGGER.log(Level.INFO, "GET request received to search for products");
        return productService.searchProducts(name, brand, price);
    }


    // Endpoint to get a specific product by Name
    @GetMapping("name/{name}")
    public List<ProductDto> getProductByName(@PathVariable String name) {
        LOGGER.log(Level.INFO, "GET request received for product with name: {0}", name);
        return productService.getProductByName(name);
    }

    // Endpoint to update an existing product
    @PutMapping("{id}")
    public Product updateProduct(@PathVariable Long id, @Valid @RequestBody Product productData, BindingResult bindingResult) {
        LOGGER.log(Level.INFO, "PUT request received to update product with ID: {0}", id);
        return productService.updateProduct(id, productData, bindingResult);
    }

    // Endpoint to delete a product by ID
    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable Long id) {
        LOGGER.log(Level.INFO, "DELETE request received to delete product with ID: {0}", id);
        productService.deleteProduct(id);
    }

}
