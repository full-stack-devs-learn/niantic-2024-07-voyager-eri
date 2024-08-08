package com.niantic.application;

import com.niantic.models.User;
import com.niantic.services.UserDao;

import java.util.Scanner;

public class BudgetTracker
{
    Scanner userInput = new Scanner(System.in);
    UserDao userDao = new UserDao();

    public void run()
    {

        while(true)
        {
            int choice = homeScreenSelection();
            switch(choice)
            {
                case 1:
                    System.out.println("add transaction");
                    break;
                case 2:
                    System.out.println("reports");
                    break;
                case 3:
                    addUser();
                    break;
                case 4:
                    System.out.println("add category");
                    break;
                case 5:
                    System.out.println("add sub category");
                    break;
                case 6:
                    System.out.println("add vendor");
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

    private void addUser()
    {
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

        System.out.printf("%s has been added!", userName);
    }

    // HELPER FUNCTIONS

    private void waitForUser()
    {
        System.out.println();
        System.out.println("Press ENTER to continue...");
    }

    private String getUserString(String message)
    {
        System.out.println(message);
        return userInput.nextLine();
    }

    private int getUserInt(String message)
    {
        return Integer.parseInt(getUserString(message));
    }
}
