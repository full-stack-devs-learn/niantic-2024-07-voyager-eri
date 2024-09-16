package com.niantic.services;

import com.niantic.models.Assignment;

import java.util.List;

public interface GradesService
{
    String[] getFileNames();

    String getFileName();

    List<Assignment> getAssignments(String fileName);

    List<Assignment> getAllAssignments(String[] fileNames);

    int getAverageScorePerStudent(List<Assignment> assignments);
}
