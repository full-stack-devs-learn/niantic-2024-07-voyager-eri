package com.niantic.services;

import com.niantic.models.Assignment;
import com.niantic.ui.UserInput;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GradesFileService implements GradesService
{
    @Override
    public String[] getFileNames()
    {
        File directory = new File("files");
        return directory.list();
    }

    @Override
    public String getFileName()
    {
        String[] fileNames = getFileNames();
        int choice = UserInput.fileSelection();

        return fileNames[choice - 1];
    }

    @Override
    public List<Assignment> getAssignments(String fileName)
    {
        List<Assignment> assignments = new ArrayList<>();
        File file = new File("files/" + fileName);

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

    @Override
    public List<Assignment> getAllAssignments(String[] fileNames)
    {
        List<Assignment> allAssignments = new ArrayList<>();

        for (String file : fileNames)
        {
            allAssignments.addAll(getAssignments(file));
        }

        return allAssignments;
    }

    @Override
    public int getAverageScore(List<Assignment> assignments)
    {
        int totalScore = 0;

        for (Assignment assignment : assignments)
        {
            totalScore += assignment.getScore();
        }

        return totalScore / assignments.size();
    }
}
