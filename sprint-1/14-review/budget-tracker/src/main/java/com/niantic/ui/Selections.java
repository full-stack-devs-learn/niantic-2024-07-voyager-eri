package com.niantic.ui;

import static com.niantic.ui.Helper.getUserInt;

public class Selections
{
    public static int homeScreenSelection()
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

    public static int reportsSelection()
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

        return Helper.getUserInt("Enter your selection: ");
    }
}
