package com.niantic.application;

import com.niantic.models.Category;
import com.niantic.models.SubCategory;
import com.niantic.models.User;
import com.niantic.services.CategoryDao;
import com.niantic.services.SubCategoryDao;
import com.niantic.services.UserDao;

import java.util.Scanner;

public class BudgetTracker
{
    Scanner userInput = new Scanner(System.in);

    CategoryDao categoryDao = new CategoryDao();
    SubCategoryDao subCategoryDao = new SubCategoryDao();
    UserDao userDao = new UserDao();

    public void run()
    {

        while(true)
        {
            int choice = homeScreenSelection();
            switch(choice)
            {
                case 1:
                    addTransaction();
                    break;
                case 2:
                    System.out.println("reports");
                    break;
                case 3:
                    addUser();
                    break;
                case 4:
                    addCategory();
                    break;
                case 5:
                    addSubCategory();
                    break;
                case 6:
                    addVendor();
                    break;
                case 0:
                    System.out.println();
                    System.out.println("Thank you for using Northwind!");
                    System.out.println("Goodbye");
                    System.out.println();
                    System.exit(0);
                default:
                    System.out.println("invalid selection");
                    break;
            }
        }

    }

    private int homeScreenSelection()
    {
        System.out.println();
        System.out.println("☆ﾟ.*･｡ﾟ Budget Tracker ﾟ｡･*.ﾟ☆ ( ´ ▽ ` )ﾉ");
        System.out.println("-".repeat(100));
        System.out.println("Select from the following options:");
        System.out.println();
        System.out.println("  1) Add Transaction");
        System.out.println("  2) Reports");
        System.out.println("  3) Add User");
        System.out.println("  4) Add Category");
        System.out.println("  5) Add Sub Category");
        System.out.println("  6) Add Vendor");
        System.out.println("  0) Quit");
        System.out.println();

        return getUserInt("Enter an option (ﾉ´ヮ`)ﾉ: ");
    }

    private void addTransaction()
    {
        System.out.println();
        System.out.println("Add a transaction!");
        System.out.println("-".repeat(100));
    }

    private void addUser()
    {
        System.out.println();
        System.out.println("Add a user!");
        System.out.println("-".repeat(100));

        String userName = getUserString("User name: ");
        String firstName = getUserString("First name: ");
        String lastName = getUserString("Last name: ");
        String phone = getUserString("Phone number: ");
        String email = getUserString("Email address: ");

        var user = new User()
        {{
            setUserName(userName);
            setFirstName(firstName);
            setLastName(lastName);
            setPhone(phone);
            setEmail(email);
        }};

        userDao.addUser(user);

        System.out.printf("User %s has been added!", userName);
    }

    private void addCategory()
    {
        System.out.println();
        System.out.println("Add a category!");
        System.out.println("-".repeat(100));

        String categoryName = getUserString("Category name: ");
        String description = getUserString("Description: ");

        var category = new Category()
        {{
            setCategoryName(categoryName);
            setDescription(description);
        }};

        categoryDao.addCategory(category);

        System.out.printf("Category %s has been added!", categoryName);
    }

    private void addSubCategory()
    {
        System.out.println();
        System.out.println("Add a subcategory!");
        System.out.println("-".repeat(100));

        int categoryId = getUserInt("Category Id: ");
        String categoryName = getUserString("Sub Category Name: ");
        String description = getUserString("Description: ");

        var subCategory = new SubCategory()
        {{
            setCategoryId(categoryId);
            setSubCategoryName(categoryName);
            setDescription(description);
        }};

        subCategoryDao.addSubCategory(subCategory);
    }

    private void addVendor()
    {
        System.out.println();
        System.out.println("Add a vendor!");
        System.out.println("-".repeat(100));
    }

    // <editor-fold desc="HELPER FUNCTIONS">

    private void waitForUser()
    {
        System.out.println();
        System.out.println("Press ENTER to continue...");
    }

    private String getUserString(String message)
    {
        System.out.print(message);
        return userInput.nextLine();
    }

    private int getUserInt(String message)
    {
        return Integer.parseInt(getUserString(message));
    }

    // </editor-fold>
}