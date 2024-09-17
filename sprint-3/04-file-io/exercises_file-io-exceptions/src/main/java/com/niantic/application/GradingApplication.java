package com.niantic.application;

import com.niantic.models.Assignment;
import com.niantic.services.GradesFileService;
import com.niantic.services.GradesService;
import com.niantic.ui.UserInput;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GradingApplication implements Runnable
{
    private static GradesService gradesService = new GradesFileService();

    public void run()
    {
        while(true)
        {
            int choice = UserInput.homeScreenSelection();
            switch(choice)
            {
                case 1:
                    displayAllFiles();
                    break;
                case 2:
                    displayFileScores();
                    break;
                case 3:
                    displayStudentAverages();
                    break;
                case 4:
                    displayAllStudentStatistics();
                    break;
                case 5:
                    displayAssignmentStatistics();
                    break;
                case 0:
                    UserInput.displayMessage("Goodbye");
                    System.exit(0);
                default:
                    UserInput.displayMessage("Please make a valid selection");
            }
        }
    }

    private void displayAllFiles()
    {
        // todo: 1 - get and display all student file names

        String[] fileNames = gradesService.getFileNames();

        for (int i = 0; i < fileNames.length; i++)
        {
            System.out.println((i + 1) + ") " + fileNames[i]);
        }
    }

    private void displayFileScores()
    {
        // todo: 2 - allow the user to select a file name
        // load all student assignment scores from the file - display all files

        String fileName = selectFileName();
        List<Assignment> assignments = gradesService.getAssignments(fileName);
        String studentName = getStudentName(assignments.getFirst());

        UserInput.displayMessage("All Assignments For " + studentName);
        System.out.println("-".repeat(60));

        for (Assignment assignment : assignments)
        {
            System.out.println(assignment);
        }
    }

    private void displayStudentAverages()
    {
        // todo: 3 - allow the user to select a file name
        // load all student assignment scores from the file - display student statistics (low score, high score, average score)

        displayAllFiles();
        String fileName = gradesService.getFileName();
        List<Assignment> assignments = gradesService.getAssignments(fileName);

        String studentName = getStudentName(assignments.getFirst());
        UserInput.displayMessage("Assignment Stats For " + studentName);
        System.out.println("-".repeat(60));
        displayStats(assignments);
    }

    private void displayAllStudentStatistics()
    {
        // todo: 4 - Optional / Challenge - load all scores from all student and all assignments
        // display the statistics for all scores (low score, high score, average score, number of students, number of assignments)

        String[] fileNames = gradesService.getFileNames();
        List<Assignment> allAssignments = gradesService.getAllAssignments(fileNames);

        UserInput.displayMessage("Assignment Stats For All Students");
        System.out.println("-".repeat(60));
        displayStats(allAssignments);
        System.out.println("Total number of students: " + fileNames.length);
        System.out.println("Total number of assignments: " + allAssignments.size());
    }

    private void displayAssignmentStatistics()
    {
        // todo: 5 - Optional / Challenge - load all scores from all student and all assignments
        // display the statistics for each assignment (assignment name, low score, high score, average score)
        // this one could take some time
    }

    private void displayStats(List<Assignment> assignments)
    {
        int averageScore = gradesService.getAverageScore(assignments);
        int lowestScore = Collections.min(assignments).getScore();
        int highestScore = Collections.max(assignments).getScore();

        System.out.println("Lowest score: " + lowestScore);
        System.out.println("Highest score: " + highestScore);
        System.out.println("Average score: " + averageScore);
    }

    private String getStudentName(Assignment assignment)
    {
        String firstName = assignment.getFirstName();
        String lastName = assignment.getLastName();
        firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1);
        lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1);

        return firstName + " " + lastName;
    }

    private String selectFileName()
    {
        displayAllFiles();
        return gradesService.getFileName();
    }
}
