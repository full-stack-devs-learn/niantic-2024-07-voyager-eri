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
    private GradesService gradesService = new GradesFileService();

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

    private String[] getAllFiles()
    {
        File file = new File("files");
        return file.list();
    }

    private String getFileName()
    {
        displayAllFiles();

        String[] fileNames = getAllFiles();
        int choice = UserInput.fileSelection();
        return fileNames[choice - 1];
    }

    private List<Assignment> getAllAssignmentsForStudent()
    {
        String fileName = getFileName();
        File file = new File("files/" + fileName);

        List<Assignment> assignments = new ArrayList<>();

        try (Scanner reader = new Scanner(file))
        {
            reader.nextLine();

            while (reader.hasNextLine())
            {
                var line = reader.nextLine();
                var columns = line.split(",");

                int number = Integer.parseInt(columns[0]);
                String firstName = columns[1];
                String lastName = columns[2];
                String assignmentName = columns[3];
                int score = Integer.parseInt(columns[4]);

                var assignment = new Assignment(number, firstName, lastName, assignmentName, score);
                assignments.add(assignment);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return assignments;
    }

    private void displayAllFiles()
    {
        // todo: 1 - get and display all student file names

        String[] fileNames = getAllFiles();

        for (int i = 0; i < fileNames.length; i++)
        {
            System.out.println((i + 1) + ") " + fileNames[i]);
        }
    }

    private void displayFileScores()
    {
        // todo: 2 - allow the user to select a file name
        // load all student assignment scores from the file - display all files

        List<Assignment> assignments = getAllAssignmentsForStudent();
        String studentName = getStudentName(assignments.getFirst());

        UserInput.displayMessage("All Assignments For " + studentName);
        System.out.println("-".repeat(60));
        for (Assignment assignment : assignments)
        {
            System.out.println(assignment);
        }
    }

    private String getStudentName(Assignment assignment)
    {
        String firstName = assignment.getFirstName();
        String lastName = assignment.getLastName();
        firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1);
        lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1);

        return firstName + " " + lastName;
    }

    private void displayStudentAverages()
    {
        // todo: 3 - allow the user to select a file name
        // load all student assignment scores from the file - display student statistics (low score, high score, average score)

        int totalScore = 0;
        int averageScore;
        int lowestScore;
        int highestScore;
        String studentName;

        List<Assignment> assignments = getAllAssignmentsForStudent();

        for (Assignment assignment : assignments)
        {
            totalScore += assignment.getScore();
        }

        averageScore = totalScore / assignments.size();

        lowestScore = Collections.min(assignments).getScore();
        highestScore = Collections.max(assignments).getScore();
        studentName = getStudentName(assignments.getFirst());

        UserInput.displayMessage("Assignment Stats For " + studentName);
        System.out.println("-".repeat(30));
        System.out.println("Lowest score: " + lowestScore);
        System.out.println("Highest score: " + highestScore);
        System.out.println("Average score: " + averageScore);
    }

    private void displayAllStudentStatistics()
    {
        // todo: 4 - Optional / Challenge - load all scores from all student and all assignments
        // display the statistics for all scores (low score, high score, average score, number of students, number of assignments)
    }

    private void displayAssignmentStatistics()
    {
        // todo: 5 - Optional / Challenge - load all scores from all student and all assignments
        // display the statistics for each assignment (assignment name, low score, high score, average score)
        // this one could take some time
    }
}
