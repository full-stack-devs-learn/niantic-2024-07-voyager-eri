package com.niantic.models;

public class SubCategory
{
    private int subCategoryId;
    private int categoryId;
    private String subCategoryName;
    private String description;

    public SubCategory(){}

    public SubCategory(int subCategoryId, int categoryId, String subCategoryName, String description)
    {
        this.subCategoryId = subCategoryId;
        this.categoryId = categoryId;
        this.subCategoryName = subCategoryName;
        this.description = description;
    }

    public int getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(int categoryId)
    {
        this.categoryId = categoryId;
    }

    public String getSubCategoryName()
    {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName)
    {
        this.subCategoryName = subCategoryName;
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
