package com.niantic.controllers.apis;

import com.niantic.models.HttpError;
import com.niantic.models.Product;
import com.niantic.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductsController
{
    private ProductDao productDao = new MySqlProductDao();
    private CategoryDao categoryDao = new MySqlCategoryDao();
    private LoggingService logger = new LoggingService();

    @GetMapping("/api/products")
    public ResponseEntity<?> getProductsByCategory(Integer catId)
    {
        try
        {
            if(categoryDao.getCategory(catId) == null)
            {
                logger.logMessage("Category with id " + catId + " not found");
                var error = new HttpError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(), "Category " + catId + " is invalid");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }

            var products = productDao.getProductsByCategory(catId);
            return ResponseEntity.ok(products);
        }
        catch (Exception e)
        {
            logger.logMessage(e.getMessage());
            var error = new HttpError(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Oops something went wrong");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/api/products/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable int productId)
    {
        try
        {
            var product = productDao.getProductById(productId);

            if(product == null)
            {
                logger.logMessage("Product with id " + productId + " not found");
                var error = new HttpError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(), "Product " + productId + " is invalid");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
            return ResponseEntity.ok(product);
        }
        catch (Exception e)
        {
            logger.logMessage(e.getMessage());
            var error = new HttpError(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Oops something went wrong");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PostMapping("/api/products")
    public ResponseEntity<?> addProduct(@RequestBody Product product)
    {
        try
        {
            var createdProduct = productDao.addProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        }
        catch (Exception e)
        {
            logger.logMessage(e.getMessage());
            var error = new HttpError(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Oops something went wrong");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PutMapping("/api/products/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@PathVariable int productId, @RequestBody Product product)
    {
        productDao.updateProduct(productId, product);
    }

    @DeleteMapping("/api/products/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable int productId)
    {
        productDao.deleteProduct(productId);
    }
}
