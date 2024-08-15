package com.niantic.controllers;

import com.niantic.models.Category;
import com.niantic.services.CategoryDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class CategoriesController
{
    CategoryDao categoriesDao = new CategoryDao();

    @GetMapping("/categories")
    public String getAllCategories(Model model)
    {
        ArrayList<Category> categories = categoriesDao.getCategories();

        StringBuilder builder = new StringBuilder();

        model.addAttribute("categories", categories);
        model.addAttribute("headTitle", "Categories | Northwind Traders");

        return "categories/index";
    }

    @GetMapping("/categories/{id}")
    public String details(Model model, @PathVariable int id)
    {
        var category = categoriesDao.getCategoryById(id);
        model.addAttribute("category", category);
        model.addAttribute("headTitle", category.getCategoryName() + " | Category Details | Northwind Traders");

        return "categories/details";
    }

    @GetMapping("/categories/add")
    public String addCategory(Model model)
    {
        model.addAttribute("category", new Category());
        model.addAttribute("action", "add");
        model.addAttribute("headTitle", "Add New Category | Northwind Traders");

        return "categories/add_edit";
    }
    
    @PostMapping("categories/add")
    public String addCategory(Model model, @ModelAttribute("category") Category category)
    {
        categoriesDao.addCategory(category);
        model.addAttribute("category", category);
        return "redirect:/categories";
    }

    @GetMapping("categories/{id}/edit")
    public String editCategory(Model model, @PathVariable int id)
    {
        Category category = categoriesDao.getCategoryById(id);

        model.addAttribute("category", category);
        model.addAttribute("action", "edit");
        model.addAttribute("headTitle", category.getCategoryName() + " | Edit Category | Northwind Traders");

        return "categories/add_edit";
    }

    @PostMapping("categories/{id}/edit")
    public String editCategory(@ModelAttribute("category") Category category, @PathVariable int id)
    {
        category.setCategoryId(id);
        categoriesDao.updateCategory(category);

        return "redirect:/categories";
    }

    @GetMapping("categories/{id}/delete")
    public String deleteCategory(Model model, @PathVariable int id)
    {
        Category category = categoriesDao.getCategoryById(id);

        if(category == null)
        {
            model.addAttribute("message", String.format("There is no category with id %d", id));
            return "404";
        }

        model.addAttribute("category", category);
        model.addAttribute("headTitle", category.getCategoryName() + " | Delete Category | Northwind Traders");
        return "categories/delete";
    }

    @PostMapping("categories/{id}/delete")
    public String deleteCategory(@PathVariable int id)
    {
        categoriesDao.deleteCategory(id);
        return "redirect:/categories";
    }
}
