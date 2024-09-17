package com.niantic.application;

import com.niantic.models.Assignment;
import com.niantic.models.Statistics;
import com.niantic.services.GradesFileService;
import com.niantic.services.GradesService;
import com.niantic.services.ReportService;
import com.niantic.ui.UserInput;

import java.io.File;
import java.util.*;

public class GradingApplication implements Runnable
{
    private static GradesService gradesService = new GradesFileService();

    public void run()
    {
        while(true)
        {
            int choice = UserInput.homeScreenSelection();
            switch (choice) {
                case 1 -> displayAllFiles();
                case 2 -> displayFileScores();
                case 3 -> displayStudentStats();
                case 4 -> displayAllStudentStatistics();
                case 5 -> displayAssignmentStatistics();
                case 6 -> createStudentSummaryReport();
                case 7 -> createAllStudentsSummaryReport();
                case 0 -> {
                    UserInput.displayMessage("Goodbye");
                    System.exit(0);
                }
                default -> UserInput.displayMessage("Please make a valid selection");
            }
        }
    }

    private void displayAllFiles()
    {
        // todo: 1 - get and display all student file names

        String[] fileNames = gradesService.getFileNames();
        UserInput.displayAllFiles(fileNames);
    }

    private void displayFileScores()
    {
        // todo: 2 - allow the user to select a file name
        // load all student assignment scores from the file - display all files

        String fileName = selectFileName();
        List<Assignment> assignments = gradesService.getAssignments(fileName);
        String studentName = parseName(fileName);
        UserInput.displayStudentScores(assignments, studentName);
    }

    private void displayStudentStats()
    {
        // todo: 3 - allow the user to select a file name
        // load all student assignment scores from the file - display student statistics (low score, high score, average score)

        String fileName = selectFileName();
        List<Assignment> assignments = gradesService.getAssignments(fileName);
        String studentName = parseName(fileName);
        Statistics statistics = new Statistics(studentName, assignments);
        UserInput.displayStudentStats(statistics);
    }

    private void displayAllStudentStatistics()
    {
        // todo: 4 - Optional / Challenge - load all scores from all student and all assignments
        // display the statistics for all scores (low score, high score, average score, number of students, number of assignments)

        String[] fileNames = gradesService.getFileNames();
        List<Assignment> allAssignments = gradesService.getAllAssignments(fileNames);
        Statistics statistics = new Statistics("All Students", allAssignments);
        UserInput.displayStudentStats(statistics);
        UserInput.displayTotalStats(statistics);
    }

    private void displayAssignmentStatistics()
    {
        // todo: 5 - Optional / Challenge - load all scores from all student and all assignments
        // display the statistics for each assignment (assignment name, low score, high score, average score)
        // this one could take some time

        String[] fileNames = gradesService.getFileNames();
        List<Assignment> allAssignments = gradesService.getAllAssignments(fileNames);
        HashMap<String, List<Integer>> assignmentsByName = gradesService.sortAssignmentsByName(allAssignments);

        UserInput.displayMessage("Stats Per Assignment");
        System.out.println("-".repeat(60));

        for(String assignmentName : assignmentsByName.keySet())
        {
            List<Integer> scores = assignmentsByName.get(assignmentName);
            int averageScore = gradesService.getAverageScoreFromAllAssignments(scores);
            int lowestScore = Collections.min(scores);
            int highestScore = Collections.max(scores);

            System.out.println(assignmentName);
            System.out.println("-".repeat(20));
            System.out.println("Lowest score: " + lowestScore);
            System.out.println("Highest score: " + highestScore);
            System.out.println("Average score: " + averageScore);
            System.out.println();
        }
    }

    private void createStudentSummaryReport()
    {
        // todo: 1
        String fileName = selectFileName();
        String studentName = parseName(fileName);
        List<Assignment> assignments = gradesService.getAssignments(fileName);
        Statistics statistics = new Statistics(studentName, assignments);

        ReportService reportService = new ReportService();
        reportService.createStudentSummaryReport(statistics);
    }

    private void createAllStudentsSummaryReport()
    {
        // todo: 2
        ReportService reportService = new ReportService();
        String[] fileNames = gradesService.getFileNames();
        List<Assignment> assignments = gradesService.getAllAssignments(fileNames);
        Statistics statistics = new Statistics("All Students", assignments);
        reportService.createAllStudentsSummaryReport(statistics);
    }

    private String parseName(String fileName)
    {
        String lowerCaseName =  fileName.replace(".csv", "")
                                        .replace("_", " ")
                                        .substring(10);
        int space = lowerCaseName.indexOf(" ");

        // this is a little cursed heh
        // all it's doing is making the name Proper Name case
        return lowerCaseName.substring(0,1).toUpperCase() + lowerCaseName.substring(1, space) + " " + lowerCaseName.substring(space + 1, space + 2).toUpperCase() + lowerCaseName.substring(space + 2);
    }

    private String selectFileName()
    {
        displayAllFiles();
        return gradesService.getFileName();
    }
}
