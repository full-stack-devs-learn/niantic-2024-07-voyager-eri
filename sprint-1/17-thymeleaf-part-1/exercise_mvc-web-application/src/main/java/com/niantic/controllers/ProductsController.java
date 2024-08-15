package com.niantic.controllers;

import com.niantic.models.Category;
import com.niantic.models.Product;
import com.niantic.services.CategoryDao;
import com.niantic.services.ProductDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class ProductsController
{
    ProductDao productsDao = new ProductDao();
    CategoryDao categoriesDao = new CategoryDao();

    @GetMapping("/products")
    public String getProductsByCategory(Model model, Integer catId)
    {
        ArrayList<Product> products;
        Category category;
        ArrayList<Category> categories = categoriesDao.getCategories();

        if(catId == null)
        {
            products = productsDao.getProductsByCategory(1);
            category = categoriesDao.getCategoryById(1);
        }
        else
        {
            products = productsDao.getProductsByCategory(catId);
            category = categoriesDao.getCategoryById(catId);

            if(category == null)
            {
                model.addAttribute("message", String.format("There is no category with id %d.", catId));
                model.addAttribute("type", "products");
                return "404";
            }
        }

        StringBuilder builder = new StringBuilder();

        model.addAttribute("products", products);
        model.addAttribute("category", category);
        model.addAttribute("categories", categories);
        model.addAttribute("headTitle", "Products in " + category.getCategoryName() + " | Northwind Traders");

        return "products/index";
    }

    @GetMapping("/products/{id}")
    public String details(Model model, @PathVariable int id)
    {
        var product = productsDao.getProduct(id);
        Category category = categoriesDao.getCategoryById(id);

        if(product == null)
        {
            model.addAttribute("message", String.format("There is no product with id %d.", id));
            model.addAttribute("type", "products");
            return "404";
        }

        model.addAttribute("product", product);
        model.addAttribute("category", category);
        model.addAttribute("headTitle", product.getProductName() + " | Product Details | Northwind Traders");

        return "products/details";
    }

    @GetMapping("/products/add")
    public String addProduct(Model model)
    {
        ArrayList<Category> categories = categoriesDao.getCategories();

        model.addAttribute("categories", categories);
        model.addAttribute("product", new Product());
        model.addAttribute("action", "add");
        model.addAttribute("headTitle", "Add New Product | Northwind Traders");

        return "products/add_edit";
    }

    @PostMapping("products/add")
    public String addProduct(Model model, @ModelAttribute("product") Product product)
    {
        productsDao.addProduct(product);
        model.addAttribute("product", product);
        return "redirect:/products";
    }

    @GetMapping("products/{id}/edit")
    public String editProduct(Model model, @PathVariable int id)
    {
        ArrayList<Category> categories = categoriesDao.getCategories();
        Product product = productsDao.getProduct(id);

        if(product == null)
        {
            model.addAttribute("message", String.format("There is no product with id %d.", id));
            model.addAttribute("type", "products");
            return "404";
        }

        model.addAttribute("categories", categories);
        model.addAttribute("product", product);
        model.addAttribute("action", "edit");
        model.addAttribute("headTitle", product.getProductName() + " | Edit Product | Northwind Traders");

        return "products/add_edit";
    }

    @PostMapping("products/{id}/edit")
    public String editProduct(@ModelAttribute("product") Product product, @PathVariable int id)
    {
        product.setProductId(id);
        productsDao.updateProduct(product);

        return "redirect:/products";
    }

    @GetMapping("products/{id}/delete")
    public String deleteProduct(Model model, @PathVariable int id)
    {
        Product product = productsDao.getProduct(id);

        if(product == null)
        {
            model.addAttribute("message", String.format("There is no product with id %d.", id));
            model.addAttribute("type", "products");
            return "404";
        }

        model.addAttribute("product", product);
        model.addAttribute("headTitle", product.getProductName() + " | Delete Product | Northwind Traders");
        return "products/delete";
    }

    @PostMapping("products/{id}/delete")
    public String deleteCategory(@PathVariable int id)
    {
        productsDao.deleteProduct(id);
        return "redirect:/products";
    }
}
