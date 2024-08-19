package com.niantic.part_1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuizTests
{
    private Quiz quiz;
    private int possiblePoints = 100;
    private String studentName = "Kirby";

    @BeforeEach
    public void setup()
    {
        quiz = new Quiz(possiblePoints, studentName);
    }

    @Test
    public void getPercent_shouldReturn_percentage()
    {
        // ARRANGE
        int score = 75;
        int expectedPercent = 75;

        // ACT
        quiz.setScore(score);
        int actualPercent = quiz.getPercent();

        // ASSERT
        assertEquals(expectedPercent,actualPercent, "Because it should have gotten the percentage by getting the fraction and multiplying by 100");
    }

    @Test
    public void getLetterGrade_shouldReturn_theCorrectLetterGrade_forA()
    {
        // ARRANGE
        int score = 100;
        String expectedLetterGrade = "A";

        // ACT
        quiz.setScore(score);
        String actualLetterGrade = quiz.getLetterGrade();

        // ASSERT
        assertEquals(expectedLetterGrade, actualLetterGrade, "Because it should have returned A if the percentage was 100");
    }

    @Test
    public void getLetterGrade_shouldReturn_theCorrectLetterGrade_forB()
    {
        // ARRANGE
        int score = 80;
        String expectedLetterGrade = "B";

        // ACT
        quiz.setScore(score);
        String actualLetterGrade = quiz.getLetterGrade();

        // ASSERT
        assertEquals(expectedLetterGrade, actualLetterGrade, "Because it should have returned B if the percentage was 80");
    }

    @Test
    public void getLetterGrade_shouldReturn_theCorrectLetterGrade_forC()
    {
        // ARRANGE
        int score = 70;
        String expectedLetterGrade = "C";

        // ACT
        quiz.setScore(score);
        String actualLetterGrade = quiz.getLetterGrade();

        // ASSERT
        assertEquals(expectedLetterGrade, actualLetterGrade, "Because it should have returned C if the percentage was 70");
    }
    @Test
    public void getLetterGrade_shouldReturn_theCorrectLetterGrade_forD()
    {
        // ARRANGE
        int score = 60;
        String expectedLetterGrade = "D";

        // ACT
        quiz.setScore(score);
        String actualLetterGrade = quiz.getLetterGrade();

        // ASSERT
        assertEquals(expectedLetterGrade, actualLetterGrade, "Because it should have returned D if the percentage was 60");
    }

    @Test
    public void getLetterGrade_shouldReturn_theCorrectLetterGrade_forF()
    {
        // ARRANGE
        int score = 59;
        String expectedLetterGrade = "F";

        // ACT
        quiz.setScore(score);
        String actualLetterGrade = quiz.getLetterGrade();

        // ASSERT
        assertEquals(expectedLetterGrade, actualLetterGrade, "Because it should have returned F if the percentage was less than 60");
    }
}