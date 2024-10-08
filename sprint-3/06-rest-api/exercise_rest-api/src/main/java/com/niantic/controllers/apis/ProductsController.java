package com.niantic.controllers.apis;

import com.niantic.models.HttpError;
import com.niantic.models.Product;
import com.niantic.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/products")
public class ProductsController
{
    private ProductDao productDao;
    private CategoryDao categoryDao;
    private LoggingService logger;

    @Autowired
    public ProductsController(ProductDao productDao, CategoryDao categoryDao, LoggingService logger)
    {
        this.productDao = productDao;
        this.categoryDao = categoryDao;
        this.logger = logger;
    }

    @GetMapping("")
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

    @GetMapping("{productId}")
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

    @PostMapping("")
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

    @PutMapping("{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable int productId, @RequestBody Product product)
    {
        try
        {
            var currentProduct = productDao.getProductById(productId);

            if(currentProduct == null)
            {
                logger.logMessage("Product with id " + productId + " not found");
                var error = new HttpError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(), "Product " + productId + " is invalid");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }

            productDao.updateProduct(productId, product);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e)
        {
            logger.logMessage(e.getMessage());
            var error = new HttpError(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Oops something went wrong");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable int productId)
    {
        try
        {
            var currentProduct = productDao.getProductById(productId);

            if(currentProduct == null)
            {
                logger.logMessage("Product with id " + productId + " not found");
                var error = new HttpError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(), "Product " + productId + " is invalid");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }

            productDao.deleteProduct(productId);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e)
        {
            logger.logMessage(e.getMessage());
            var error = new HttpError(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Oops something went wrong");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
