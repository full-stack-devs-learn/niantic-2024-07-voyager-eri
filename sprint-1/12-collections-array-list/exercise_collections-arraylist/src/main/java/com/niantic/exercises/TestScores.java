package com.niantic.exercises;
import com.niantic.models.TestScore;
import java.util.ArrayList;

public class TestScores
{

    /*
    1.  An ArrayList of TestScores contains test results for all students and all of their tests
        Given an input of All testScores and the name of a test - return all scores for the
        requested tests.
     */
    public ArrayList<TestScore> getScoresByTest(ArrayList<TestScore> testScores, String testName)
    {
        ArrayList<TestScore> scoresByTestName = new ArrayList<TestScore>();

        for (var score : testScores)
        {
            if (score.getTestName().equals(testName))
            {
                scoresByTestName.add(score);
            }
        }
        return scoresByTestName;
    }

    /*
    2.  An ArrayList of TestScores contains test results for all students and all of their tests
        Given an input of All testScores and the name of a student - return all scores for the
        requested student.
     */
    public ArrayList<TestScore> getScoresByStudent(ArrayList<TestScore> testScores, String student)
    {
        ArrayList<TestScore> scoresByStudent = new ArrayList<TestScore>();

        for (var score : testScores)
        {
            if (score.getStudentName().equals(student))
            {
                scoresByStudent.add(score);
            }
        }
        return scoresByStudent;
    }

    /*
    3.  An ArrayList of TestScores contains test results for all students and all of their tests
        Given an input of All testScores return the highest score.
     */
    public int getHighestScore(ArrayList<TestScore> testScores)
    {
        int highestScore = 0;

        for (var score : testScores)
        {
            highestScore = Math.max(score.getScore(), highestScore);
        }

        return highestScore;
    }

    /*
    4.  An ArrayList of TestScores contains test results for all students and all of their tests
        Given an input of All testScores return the lowest score.
     */
    public int getLowestScore(ArrayList<TestScore> testScores)
    {
        int lowestScore = 100;

        for (var score : testScores)
        {
            lowestScore = Math.min(score.getScore(), lowestScore);
        }
        return lowestScore;
    }

    /*
    5.  An ArrayList of TestScores contains test results for all students and all of their tests
        Given an input of All testScores return the lowest score.
     */
    public int getAverageScore(ArrayList<TestScore> testScores)
    {
        int total = 0;

        for (var score: testScores)
        {
            total += score.getScore();
        }
        return total/testScores.size();
    }

    /*
    6.  An ArrayList of TestScores contains test results for all students and all of their tests
        Given an input of All testScores return the highest score for the specified test name.
     */
    public int getHighestScoreByTest(ArrayList<TestScore> testScores, String testName)
    {
        int highestScore = 0;

        for (var score : testScores)
        {
            if (score.getTestName().equals(testName))
            {
                highestScore = Math.max(highestScore, score.getScore());
            }
        }
        return highestScore;
    }

    /*
    7.  An ArrayList of TestScores contains test results for all students and all of their tests
        Given an input of All testScores return the lowest score for the specified test name.
     */
    public int getLowestScoreByTest(ArrayList<TestScore> testScores, String testName)
    {
        int lowestScore = 100;

        for (var score : testScores)
        {
            if (score.getTestName().equals(testName))
            {
                lowestScore = Math.min(lowestScore, score.getScore());
            }
        }
        return lowestScore;
    }

    /*
    8.  An ArrayList of TestScores contains test results for all students and all of their tests
        Given an input of All testScores return the average score for the specified test name.
     */
    public int getAverageScoreByTest(ArrayList<TestScore> testScores, String testName)
    {
        int totalScores = 0;
        int totalNumberOfScores = 0;

        for (var score : testScores)
        {
            if (score.getTestName().equals(testName))
            {
                totalScores += score.getScore();
                totalNumberOfScores++;
            }
        }
        return totalScores / totalNumberOfScores;
    }

    /*
    9.  An ArrayList of TestScores contains test results for all students and all of their tests
        Given an input of All testScores return the highest score for the specified student.
     */
    public int getHighestScoreByStudent(ArrayList<TestScore> testScores, String student)
    {
        int highestScore = 0;

        for (var score : testScores)
        {
            if (score.getStudentName().equals(student))
            {
                highestScore = Math.max(highestScore, score.getScore());
            }
        }
        return highestScore;
    }

    /*
    10.  An ArrayList of TestScores contains test results for all students and all of their tests
        Given an input of All testScores return the lowest score for the specified student.
     */
    public int getLowestScoreByStudent(ArrayList<TestScore> testScores, String student)
    {
        int lowestScore = 100;

        for (var score : testScores)
        {
            if (score.getStudentName().equals(student))
            {
                lowestScore = Math.min(lowestScore, score.getScore());
            }
        }
        return lowestScore;
    }

    /*
    11.  An ArrayList of TestScores contains test results for all students and all of their tests
        Given an input of All testScores return the average score for the specified student.
     */
    public int getAverageScoreByStudent(ArrayList<TestScore> testScores, String student)
    {
        int totalScores = 0;
        int totalNumberOfScores = 0;

        for (var score : testScores)
        {
            if (score.getStudentName().equals(student))
            {
                totalScores += score.getScore();
                totalNumberOfScores++;
            }
        }
        return totalScores / totalNumberOfScores;
    }
}
