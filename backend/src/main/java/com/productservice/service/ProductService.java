package com.productservice.service;


import com.productservice.dto.ProductDto;
import com.productservice.model.Product;
import org.springframework.validation.BindingResult;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<Product> getAllProducts();

    ProductDto createProduct(ProductDto productDto) throws IOException;

    void validationError(BindingResult bindingResult);

    Product getProductById(Long id);

    List<ProductDto> getProductByName(String name);

    List<Product> getProductByBrand(String brand);

    Product updateProduct(Long id, Product productData, BindingResult bindingResult);

    void deleteProduct(Long id);

    List<ProductDto> searchProducts(String name, String brand, Long price);


}
