package com.niantic.models;

public class Category {
    private int categoryId;
    private String categoryName;
    private String description;

    public Category(){}

    public Category(int categoryId, String categoryName, String description)
    {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}