package com.productservice.service;

import com.productservice.dto.ProductDto;
import com.productservice.enums.Constants;
import com.productservice.exception.InvalidProductDataException;
import com.productservice.exception.ProductNotFoundException;
import com.productservice.model.Product;
import com.productservice.model.Review;
import com.productservice.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    /**
     * Retrieve all products.
     *
     * @return The List of products.
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Create a new product.
     * @return The created product.
     */
    public ProductDto createProduct(ProductDto productDto) throws IOException {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setBrand(productDto.getBrand());
        product.setImg(productDto.getImg().getBytes());
        return productRepository.save(product).getDto();
    }

    /**
     * Handle validation errors.
     *
     * @param bindingResult The BindingResult object containing validation errors.
     * @throws InvalidProductDataException if validation errors are present.
     */
    public void validationError(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            List<String> errors = new ArrayList<>();

            // Convert each field error to a string representation
            for (FieldError fieldError : fieldErrors) {
                errors.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
            }

            // Throw an exception with the validation errors
            throw new InvalidProductDataException("Validation Failed!", LocalDateTime.now(), errors);
        }
    }

    /**
     * Retrieve a product by ID.
     *
     * @param id The ID of the product.
     * @return The retrieved product.
     * @throws ProductNotFoundException if the product is not found.
     */
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(Constants.ERROR_PRODUCT_NOT_FOUND.getMessage() + id));
    }

    /**
     * Retrieve products by name.
     *
     * @param name The name of the products.
     * @return The list of products with the given name.
     * @throws ProductNotFoundException if no products are found with the given name.
     */
    public List<ProductDto> getProductByName(String name) {
        List<ProductDto> products = productRepository.findAllByName(name).stream().map(Product::getDto).collect(Collectors.toList());
        if (products.isEmpty()) {
            throw new ProductNotFoundException(Constants.ERROR_PRODUCT_NOT_FOUND.getMessage() + name);
        }
        return products;
    }


    /**
     * Retrieve products by brand.
     *
     * @param brand The brand of the products.
     * @return The list of products with the given brand.
     * @throws ProductNotFoundException if no products are found with the given brand.
     */
    public List<Product> getProductByBrand(String brand) {
        List<Product> products = productRepository.findAllByBrand(brand);
        if (products.isEmpty()) {
            throw new ProductNotFoundException(Constants.ERROR_PRODUCT_NOT_FOUND.getMessage() + brand);
        }
        return products;
    }

    /**
     * Update a product.
     *
     * @param id            The ID of the product to be updated.
     * @param productData   The updated product data.
     * @param bindingResult The BindingResult object for validation.
     * @return The updated product.
     */
    public Product updateProduct(Long id, Product productData, BindingResult bindingResult) {
        // Check for validation errors
        validationError(bindingResult);

        Product product = getProductById(id);

        product.setName(productData.getName());
        product.setDescription(productData.getDescription());
        product.setBrand(productData.getBrand());
        product.setPrice(productData.getPrice());


        return productRepository.save(product);
    }

    /**
     * Delete a product by ID.
     *
     * @param id The ID of the product to be deleted.
     */
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }


    /**
     * Search for products based on provided parameters.
     *
     * @param name     The name of the products to search for.
     * @param brand    The brand of the products to search for.
     * @return The list of products matching the search criteria.
     */
    public List<ProductDto> searchProducts(String name, String brand, Long price) {
        // Perform the search based on the provided parameters
        List<ProductDto> searchResults = new ArrayList<>();

        // Search by name
//        if (name != null && !name.isEmpty()) {
//            searchResults.addAll(getProductByName(name).stream().map(Product::getDto).toList());
//        }

        // Search by brand
        if (brand != null && !brand.isEmpty()) {
            searchResults.addAll(getProductByBrand(brand).stream().map(Product::getDto).toList());
        }
        //Search By price
        if (price != null){
            searchResults.addAll(getProductByPrice(price).stream().map(Product::getDto).toList());
        }
        if(brand==null && name==null && price==null){
            searchResults.addAll(getAllProducts().stream().map(Product::getDto).toList());
        }
        if(Objects.equals(brand, "") || Objects.equals(name, "")){
            searchResults.addAll(getAllProducts().stream().map(Product::getDto).toList());
        }

        return searchResults;
    }

    private Collection<? extends Product> getProductByPrice(Long price) {
        return productRepository.findAllByPrice(price);
    }
}
