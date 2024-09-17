package com.niantic.services;

import com.niantic.models.Statistics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReportService
{
    private static GradesService gradesService = new GradesFileService();

    private static void ensureDirectoryExists(String path)
    {
        File dir = new File(path);
        if(!dir.exists())
        {
            dir.mkdir();
        }
    }
    public void createStudentSummaryReport(Statistics statistics)
    {
        ensureDirectoryExists("reports");

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fileName = "reports/" + today.format(formatter) + "_" + statistics.getStudentName().toLowerCase().replace(" ", "_") + ".txt";

        File file = new File(fileName);

        try(PrintWriter out = new PrintWriter(file))
        {
            out.println(statistics.getStudentName());
            out.println("-".repeat(20));
            out.println("Low Score: " + statistics.getLowestScore());
            out.println("High Score: " + statistics.getHighestScore());
            out.println("Average Score: " + statistics.getAverageScore());
        }
        catch (FileNotFoundException e)
        {

        }
    }

    public void createAllStudentsSummaryReport(Statistics statistics)
    {
        ensureDirectoryExists("reports");
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fileName = "reports/" + today.format(formatter) + "_" + "all_students" + ".txt";

        File file = new File(fileName);

        try (PrintWriter out = new PrintWriter(file))
        {
            out.println(statistics.getStudentName());
            out.println("-".repeat(20));
            out.println("Low Score: " + statistics.getLowestScore());
            out.println("High Score: " + statistics.getHighestScore());
            out.println("Average Score: " + statistics.getAverageScore());
            out.println("Total number of students: " + gradesService.getFileNames().length);
            out.println("Total number of assignments: " + statistics.getTotalNumberOfAssignments());
        }
        catch (FileNotFoundException e)
        {

        }
    }
}
