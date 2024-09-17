package com.niantic.ui;

import com.niantic.models.Assignment;
import com.niantic.models.Statistics;
import com.niantic.services.GradesFileService;
import com.niantic.services.GradesService;

import java.util.List;
import java.util.Scanner;

public class UserInput
{
    protected static Scanner in = new Scanner(System.in);
    private static GradesService gradesService = new GradesFileService();

    public static int homeScreenSelection()
    {
        System.out.println();
        System.out.println("What do you want to do?");
        System.out.println("-".repeat(30));
        System.out.println();
        System.out.println("  1) Display files");
        System.out.println();
        System.out.println("  ------------ Individual File ------------");
        System.out.println("  2) Student: display all scores");
        System.out.println("  3) Student: display average score");
        System.out.println();
        System.out.println("  ---------- Challenge All Files ----------");
        System.out.println("  4) All Students: display average score");
        System.out.println("  5) All Assignments: display average score");
        System.out.println();
        System.out.println("  ---------- Create Report Files ----------");
        System.out.println("  6) Create student summary report");
        System.out.println("  7) Create all students summary report");
        System.out.println();
        System.out.println("  0) Exit");

        System.out.println();
        System.out.print("Please make a selection: ");

        return Integer.parseInt(in.nextLine());
    }

    public static void displayAllFiles(String[] fileNames)
    {
        for (int i = 0; i < fileNames.length; i++)
        {
            System.out.println((i + 1) + ") " + fileNames[i]);
        }
    }

    public static void displayStudentScores(List<Assignment> assignments, String studentName)
    {
        UserInput.displayMessage("All Assignments For " + studentName);
        System.out.println("-".repeat(60));

        for (Assignment assignment : assignments)
        {
            System.out.println(assignment);
        }
    }

    public static void displayStudentStats(Statistics statistics)
    {
        displayMessage("Stats For " + statistics.getStudentName());
        System.out.println("-".repeat(30));
        System.out.println("Low Score: " + statistics.getLowestScore());
        System.out.println("High Score: " + statistics.getHighestScore());
        System.out.println("Average Score: " + statistics.getAverageScore());
    }

    public static void displayTotalStats(Statistics statistics)
    {
        System.out.println("Total number of students: " + gradesService.getFileNames().length);
        System.out.println("Total number of assignments: " + statistics.getTotalNumberOfAssignments());
    }

    public static int fileSelection()
    {
        System.out.print("Enter the number of the file you would like to view: ");
        return Integer.parseInt(in.nextLine());
    }

    public static void displayMessage(String message)
    {
        System.out.println();
        System.out.println(message);
    }
}
