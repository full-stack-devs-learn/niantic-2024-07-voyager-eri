package com.niantic;

import java.util.Arrays;
import java.util.Scanner;

public class Main
{
    private static Scanner userInput = new Scanner(System.in);
    private static int[] scores = new int[0];

    public static void main(String[] args)
    {
        while(true)
        {
            int choice = getHomeSelection();

            switch(choice)
            {
                case 1:
                    createNewTestScores();
                    break;
                case 2:
                    calculateAverage(scores);
                    break;
                case 3:
                    findHighestScore(scores);
                    break;
                case 4:
                    findLowestScore(scores);
                    break;
                case 5:
                    System.out.println("Thanks for playing!");
                    System.out.println("Good bye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid selection!");
                    break;
            }
        }
    }

    public static int getHomeSelection()
    {
        System.out.println();
        System.out.println("Welcome to Test Score Tools");
        System.out.println("------------------------------");
        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println("1) Enter new test scores");
        System.out.println("2) Calculate the class average");
        System.out.println("3) Find the highest score");
        System.out.println("4) Find the lowest score");
        System.out.println("0) Exit");

        System.out.print("Please select an option:");
        return Integer.parseInt(userInput.nextLine());
    }

    private static void createNewTestScores()
    {
        int numberOfScores;

        System.out.println();
        System.out.println("How many scores would you like to enter?");
        numberOfScores = Integer.parseInt(userInput.nextLine());

        scores = new int[numberOfScores];

        System.out.println("Add your scores one at a time, pressing enter after every score");

        for (int i = 0; i < scores.length; i++)
        {
            scores[i] = Integer.parseInt(userInput.nextLine());
        }

    }

    private static void calculateAverage(int[] scores)
    {
        int sum = 0;
        double average;

        for (int score : scores)
        {
            sum += score;
        }

        average = (double)sum / scores.length;

        System.out.println();
        System.out.println("Average of all your test scores: " + average);
    }

    private static void findHighestScore(int[] scores)
    {
        int curHighest = scores[0];

        for (int score : scores)
        {
            if (score > curHighest)
            {
                curHighest = score;
            }
        }

        System.out.println();
        System.out.println("Highest score of all tests: " + curHighest);
    }

    private static void findLowestScore(int[] scores)
    {
        int curLowest = scores[0];

        for (int score : scores)
        {
            if (score < curLowest)
            {
                curLowest = score;
            }
        }

        System.out.println();
        System.out.println("Lowest score of all tests: " + curLowest);
    }
}