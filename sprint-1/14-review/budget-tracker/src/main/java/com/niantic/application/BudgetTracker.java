package com.niantic.application;

import com.niantic.models.*;
import com.niantic.services.*;
import com.niantic.ui.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;


public class BudgetTracker
{
    // <editor-fold desc="INITIALIZING">

    Scanner userInput = new Scanner(System.in);
    CategoryDao categoryDao = new CategoryDao();
    SubCategoryDao subCategoryDao = new SubCategoryDao();
    static TransactionDao transactionDao = new TransactionDao();
    UserDao userDao = new UserDao();
    VendorDao vendorDao = new VendorDao();

    // </editor-fold>

    // <editor-fold desc="MENU">

    public void run()
    {
        while(true)
        {
            int choice = Selections.homeScreenSelection();
            switch(choice)
            {
                case 1 -> addTransaction();
                case 2 -> displayReports();
                case 3 -> addUser();
                case 4 -> addCategory();
                case 5 -> addSubCategory();
                case 6 -> addVendor();
                case 0 ->
                {
                    System.out.println();
                    System.out.println("Thank you for using Northwind!");
                    System.out.println("Goodbye");
                    System.out.println();
                    System.exit(0);
                }
                default -> System.out.println("invalid selection");
            }
        }
    }

    private void displayReports()
    {
        while(true)
        {
            int choice = Selections.reportsSelection();
            switch(choice)
            {
                case 1 -> getTransactions("user");
                case 2 -> getTransactions("month");
                case 3 -> getTransactions("year");
                case 4 -> getTransactions("sub category");
                case 5 -> getTransactions("category");
                case 6 -> getTransactionById();
                case 0 -> {return;}
                default -> System.out.println("That was an invalid selection, please select from the available options.");
            }
        }
    }

    // </editor-fold>

    // <editor-fold desc="REPORT FUNCTION">

    public static void getTransactions(String reportType)
    {
        System.out.println();
        System.out.println("Get transactions by " + reportType + "!");
        System.out.println("-".repeat(50));

        int filterKey = Helper.getUserInt("Enter " + reportType + ": ");
        System.out.println();

        ArrayList<Transaction> transaction = switch (reportType) {
            case "user" -> transactionDao.getTransactionByUser(filterKey);
            case "month" -> transactionDao.getTransactionByMonth(filterKey);
            case "year" -> transactionDao.getTransactionByYear(filterKey);
            case "sub category" -> transactionDao.getTransactionBySubCategory(filterKey);
            case "category" -> transactionDao.getTransactionByCategory(filterKey);
            default -> throw new IllegalStateException("Unexpected value: " + reportType);
        };

        System.out.println("Transactions for " + reportType + " " + filterKey);
        System.out.println("-".repeat(50));
        System.out.printf("%-13s %-11s %-15s%n", "Date", "Amount", "Notes");
        System.out.println("-".repeat(50));

        for (var eachTransaction : transaction)
        {
            System.out.printf("%-13tF $%-10.2f %-15s%n", eachTransaction.getDate(), eachTransaction.getAmount(), eachTransaction.getNotes());
        }

        Helper.waitForUser();
    }

    public void getTransactionById()
    {
        System.out.println();
        System.out.println("Get a transaction by its id!");
        System.out.println("-".repeat(50));

        int transactionId = Helper.getUserInt("Enter transaction id: ");
        System.out.println();
        var transaction = transactionDao.getTransactionById(transactionId);

        System.out.println("Transaction for id " + transactionId);
        System.out.println("-".repeat(50));
        System.out.printf("%-13s %-11s %-15s%n", "Date", "Amount", "Notes");
        System.out.println("-".repeat(50));
        System.out.printf("%-13tF $%-10.2f %-15s%n", transaction.getDate(), transaction.getAmount(), transaction.getNotes());
    }

    // </editor-fold>

    // <editor-fold desc="ADD FUNCTIONS">
    private void addTransaction()
    {
        System.out.println();
        System.out.println("Add a transaction!");
        System.out.println("-".repeat(50));

        int userId = Helper.getUserInt("User Id: ");
        int subCategoryId = Helper.getUserInt("Sub Category Id: ");
        int vendorId = Helper.getUserInt("Vendor Id: ");
        LocalDate date = LocalDate.parse(Helper.getUserString("Date (format YYYY-MM-DD): "));
        System.out.print("Amount: ");
        BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(userInput.nextLine()));
        String notes = Helper.getUserString("Notes: ");

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

        String userName = Helper.getUserString("User name: ");
        String firstName = Helper.getUserString("First name: ");
        String lastName = Helper.getUserString("Last name: ");
        String phone = Helper.getUserString("Phone number: ");
        String email = Helper.getUserString("Email address: ");

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

        String categoryName = Helper.getUserString("Category name: ");
        String description = Helper.getUserString("Description: ");

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

        int categoryId = Helper.getUserInt("Category Id: ");
        String categoryName = Helper.getUserString("Sub Category Name: ");
        String description = Helper.getUserString("Description: ");

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

        String vendorName = Helper.getUserString("Vendor name: ");
        String website = Helper.getUserString("Website: ");

        var vendor = new Vendor()
        {{
            setVendorName(vendorName);
            setWebsite(website);
        }};

        vendorDao.addVendor(vendor);

        System.out.printf("Vendor %s has been added!", vendorName);
    }

    // </editor-fold>
    
}