package com.niantic.northwind_models;

public class Category
{
    // Make your variables private, even if you have a public class
    private int id;
    private String name;
    private String description;

    // This is a constructor. No return type. This name has to match the class name.
    // Allows us to initialize default values in our objects.
    public Category(int id, String name, String description)
    {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // We can create an empty constructor so that we can make an
    // object later WITHOUT parameters.
    public Category(){}

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {

    }
}
