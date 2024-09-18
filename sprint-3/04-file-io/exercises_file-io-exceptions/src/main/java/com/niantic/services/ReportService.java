package com.niantic.services;

import com.niantic.models.Assignment;
import com.niantic.models.Statistics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReportService
{
    private static GradesService gradesService = new GradesFileService();
    private final LogService errorLogger = new LogService("error");
    private final LogService appLogger = new LogService("application");

    private void ensureDirectoryExists(String path)
    {
        File dir = new File(path);
        if(!dir.exists())
        {
            dir.mkdir();
            appLogger.logMessage("Creating directory " + path);
        }
    }

    public void createReport(String fileNameSuffix, Statistics statistics)
    {
        ensureDirectoryExists("reports");

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fileName = "reports/" + today.format(formatter) + "_" + fileNameSuffix + ".txt";

        File file = new File(fileName);

        try(PrintWriter out = new PrintWriter(file))
        {
            out.println(statistics.getStudentName());
            out.println("-".repeat(20));

            if(fileNameSuffix.equals("all_students"))
            {
                out.println("Total number of students: " + gradesService.getFileNames().length);
                out.println("Total number of assignments: " + statistics.getTotalNumberOfAssignments());
            }

            out.println("Low Score: " + statistics.getLowestScore());
            out.println(statistics.getLowestScoreAssignment());
            out.println();
            out.println("High Score: " + statistics.getHighestScore());
            out.println(statistics.getHighestScoreAssignment());
            out.println();

            out.println("Average Score: " + statistics.getAverageScore());
            var averageRangeAssignments = statistics.getAverageRangeAssignments();

            for (Assignment assignment : averageRangeAssignments)
            {
                out.println(assignment);
            }
        }
        catch (FileNotFoundException e)
        {
            errorLogger.logMessage(e.getMessage());
        }
    }
}
