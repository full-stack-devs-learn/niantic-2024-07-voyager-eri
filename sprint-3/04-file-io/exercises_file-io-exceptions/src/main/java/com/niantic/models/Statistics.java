package com.niantic.models;

import com.niantic.services.GradesFileService;
import com.niantic.services.GradesService;

import java.util.Collections;
import java.util.List;

public class Statistics
{
    private String studentName;
    private List<Assignment> assignments;

    private static GradesService gradesService = new GradesFileService();

    public Statistics(String studentName, List<Assignment> assignments)
    {
        this.studentName = studentName;
        this.assignments = assignments;
    }

    public String getStudentName()
    {
        return studentName;
    }

    public List<Assignment> getAssignments()
    {
        return assignments;
    }

    public int getTotalNumberOfAssignments()
    {
        int totalStudents = gradesService.getFileNames().length;
        return assignments.size() / totalStudents;
    }

    public int getLowestScore()
    {
        return Collections.min(assignments).getScore();
    }

    public Assignment getLowestScoreAssignment()
    {
        return Collections.min(assignments);
    }

    public int getHighestScore()
    {
        return Collections.max(assignments).getScore();
    }

    public Assignment getHighestScoreAssignment()
    {
        return Collections.max(assignments);
    }

    public int getAverageScore()
    {
        int totalScore = 0;

        for (Assignment assignment : assignments)
        {
            totalScore += assignment.getScore();
        }

        return totalScore / assignments.size();
    }

    public List<Assignment> getAverageRangeAssignments()
    {
        int averageScore = getAverageScore();
        return assignments.stream()
                          .filter(assignment -> assignment.getScore() >= averageScore - 1 && assignment.getScore() <= averageScore + 1)
                          .toList();


    }
}
