package com.niantic.controllers.apis;

import com.niantic.models.Product;
import com.niantic.services.MySqlProductDao;
import com.niantic.services.ProductDao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductsController
{
    private ProductDao productDao = new MySqlProductDao();

    @GetMapping("/api/products")
    public List<Product> getProductsByCategory(int catId)
    {
        return productDao.getProductsByCategory(catId);
    }

    @GetMapping("/api/products/{productId}")
    public Product getProductById(@PathVariable int productId)
    {
        return productDao.getProductById(productId);
    }
}
