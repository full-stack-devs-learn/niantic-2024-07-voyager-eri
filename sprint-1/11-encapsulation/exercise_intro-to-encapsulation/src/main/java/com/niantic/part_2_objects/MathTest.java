package com.niantic.part_2_objects;

public class MathTest
{
    private int score;
    private int possiblePoints;
    private String studentName;

    public MathTest(int possiblePoints, String studentName)
    {
        this.possiblePoints = possiblePoints;
        this.studentName = studentName;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public int getPossiblePoints()
    {
        return possiblePoints;
    }

    public String getStudentName()
    {
        return studentName;
    }

    public String getLetterGrade()
    {
        String letter = "F";

        switch(getPercent()/10)
        {
            case 9:
                letter = "A";
                break;
            case 8:
                letter = "B";
                break;
            case 7:
                letter = "C";
                break;
            case 6:
                letter = "D";
                break;
        }

        return letter;
    }

    public int getPercent()
    {
        return score * 100 / possiblePoints;
    }
}
