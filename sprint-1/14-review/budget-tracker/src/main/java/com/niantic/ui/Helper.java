package com.niantic.ui;

import java.util.Scanner;

public class Helper
{
    static Scanner userInput = new Scanner(System.in);

    public static void waitForUser()
    {
        System.out.println();
        System.out.println("Press ENTER to continue...");
        userInput.nextLine();
    }

    public static String getUserString(String message)
    {
        System.out.print(message);
        return userInput.nextLine();
    }

    public static int getUserInt(String message)
    {
        return Integer.parseInt(getUserString(message));
    }
}
