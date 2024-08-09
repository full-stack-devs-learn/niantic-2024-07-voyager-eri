package com.niantic.application;

import com.niantic.models.*;
import com.niantic.services.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;


public class BudgetTracker
{
    // <editor-fold desc="INITIALIZING">

    Scanner userInput = new Scanner(System.in);
    CategoryDao categoryDao = new CategoryDao();
    SubCategoryDao subCategoryDao = new SubCategoryDao();
    TransactionDao transactionDao = new TransactionDao();
    UserDao userDao = new UserDao();
    VendorDao vendorDao = new VendorDao();

    // </editor-fold>

    // <editor-fold desc="MENU">

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
                    displayReports();
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
        System.out.println("-".repeat(70));
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

    private void displayReports()
    {
        while(true)
        {
            int choice = reportsSelection();
            switch(choice)
            {
                case 1:
                    getTransactionByUser();
                    break;
                case 2:
                    getTransactionsByMonth();
                    break;
                case 3:
                    System.out.println("by year");
                    break;
                case 4:
                    System.out.println("by sub category");
                    break;
                case 5:
                    System.out.println("by category");
                case 0:
                    return;
                default:
                    System.out.println("That was an invalid selection, please select from the available options.");
            }
        }
    }

    private int reportsSelection()
    {
        System.out.println();
        System.out.println("Reports");
        System.out.println("--------------------------------------");
        System.out.println("Select from the following options:");
        System.out.println();
        System.out.println("  1) Transactions by User");
        System.out.println("  2) Transactions by Month");
        System.out.println("  3) Transactions by Year");
        System.out.println("  4) Transactions by Sub Category");
        System.out.println("  5) Transactions by Category");
        System.out.println("  0) Back");
        System.out.println();

        return getUserInt("Enter your selection: ");
    }

    // </editor-fold>

    // <editor-fold desc="REPORT FUNCTIONS">

    private void getTransactionByUser()
    {
        System.out.println();
        System.out.println("Get transactions by user!");
        System.out.println("-".repeat(50));

        int userId = getUserInt("Enter user id: ");
        System.out.println();

        var transaction = transactionDao.getTransactionByUser(userId);

        // System.out.println("Date" + " ".repeat(10) + "Amount" + " ".repeat(10) + "Notes" + " ".repeat(10));
        System.out.println("Transactions for " + userId);
        System.out.println("-".repeat(50));

        for (var eachTransaction : transaction)
        {
            System.out.printf("%-13tF %-10.2f %-15s%n", eachTransaction.getDate(), eachTransaction.getAmount(), eachTransaction.getNotes());
        }

        waitForUser();
    }

    private void getTransactionsByMonth()
    {
        System.out.println();
        System.out.println("Get transactions by month!");
        System.out.println("-".repeat(50));

        int month = getUserInt("Enter month number: ");
        System.out.println();

        var transaction = transactionDao.getTransactionByMonth(month);

        System.out.println("Transactions for month " + month);
        System.out.println("-".repeat(50));

        for (var eachTransaction : transaction)
        {
            System.out.printf("%-13tF %-10.2f %-15s%n", eachTransaction.getDate(), eachTransaction.getAmount(), eachTransaction.getNotes());
        }

        waitForUser();
    }

    // </editor-fold>

    // <editor-fold desc="ADD FUNCTIONS">
    private void addTransaction()
    {
        System.out.println();
        System.out.println("Add a transaction!");
        System.out.println("-".repeat(50));

        int userId = getUserInt("User Id: ");
        int subCategoryId = getUserInt("Sub Category Id: ");
        int vendorId = getUserInt("Vendor Id: ");
        LocalDate date = LocalDate.parse(getUserString("Date (format YYYY-MM-DD): "));
        System.out.print("Amount: ");
        BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(userInput.nextLine()));
        String notes = getUserString("Notes: ");

        var transaction = new Transaction()
        {{
            setUserId(userId);
            setSubCategoryId(subCategoryId);
            setVendorId(vendorId);
            setDate(date);
            setAmount(amount);
            setNotes(notes);
        }};

        transactionDao.addTransaction(transaction);

        System.out.println("Transaction has been added!");
    }

    private void addUser()
    {
        System.out.println();
        System.out.println("Add a user!");
        System.out.println("-".repeat(50));

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
        System.out.println("-".repeat(50));

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
        System.out.println("-".repeat(50));

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
        System.out.println("-".repeat(50));

        String vendorName = getUserString("Vendor name: ");
        String website = getUserString("Website: ");

        var vendor = new Vendor()
        {{
            setVendorName(vendorName);
            setWebsite(website);
        }};

        vendorDao.addVendor(vendor);

        System.out.printf("Vendor %s has been added!", vendorName);
    }

    // </editor-fold>

    // <editor-fold desc="HELPER FUNCTIONS">

    private void waitForUser()
    {
        System.out.println();
        System.out.println("Press ENTER to continue...");
        userInput.nextLine();
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