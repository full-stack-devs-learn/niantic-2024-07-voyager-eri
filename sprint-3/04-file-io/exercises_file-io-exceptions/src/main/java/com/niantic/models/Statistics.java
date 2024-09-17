package com.niantic.models;

import java.util.Collections;
import java.util.List;

public class Statistics
{
    private String studentName;
    private List<Assignment> assignments;

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

    public int getLowestScore()
    {
        return Collections.min(assignments).getScore();
    }

    public int getHighestScore()
    {
        return Collections.max(assignments).getScore();
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
}
