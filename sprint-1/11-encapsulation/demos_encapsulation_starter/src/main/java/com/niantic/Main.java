package com.niantic;
import com.niantic.northwind_models.Category;

public class Main
{
    public static void main(String[] args)
    {
        // Category is the datatype of the variable
        // category is the variable name
        // Category() is the construction of the object

        Category category;
        category = new Category(1, "Beverages", "Drink related");

        System.out.println(category.getId());
    }
}