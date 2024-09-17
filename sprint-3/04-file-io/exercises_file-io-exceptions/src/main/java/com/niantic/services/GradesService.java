package com.niantic.services;

import com.niantic.models.Assignment;

import java.util.HashMap;
import java.util.List;

public interface GradesService
{
    String[] getFileNames();

    String getFileName();

    List<Assignment> getAssignments(String fileName);

    List<Assignment> getAllAssignments(String[] fileNames);

    HashMap<String, List<Integer>> sortAssignmentsByName(List<Assignment> allAssignments);

    int getAverageScore(List<Assignment> assignments);

    int getAverageScoreFromAllAssignments(List<Integer> scores);
}
