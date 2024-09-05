package com.niantic.controllers.apis;

import com.niantic.models.Product;
import com.niantic.services.ProductDao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ProductsApiController
{
    private ProductDao productDao = new ProductDao();

    @GetMapping("/api/products/category/{catId}")
    public ArrayList<Product> productsByCategory(@PathVariable int catId)
    {
        return productDao.getProductsByCategory(catId);
    }
}
