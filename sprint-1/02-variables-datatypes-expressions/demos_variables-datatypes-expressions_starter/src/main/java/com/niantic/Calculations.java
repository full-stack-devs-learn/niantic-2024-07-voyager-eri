package com.niantic;

public class Calculations
{
    public static void main(String[] args)
    {
        // Calculate how many donuts per student
        int numberOfStudents = 4;
        int numberofDonuts = 22;

        int donutsPerStudent = numberofDonuts / numberOfStudents;
        int remainingDonuts = numberofDonuts % numberOfStudents;
        double donutsPerStudentDouble = (double)numberofDonuts / numberOfStudents;

        System.out.println("Number of donuts per student");
        System.out.println("Total donuts: " + numberofDonuts);
        System.out.println("Total students: " + numberOfStudents);
        System.out.println("Donuts per student: " + donutsPerStudent);
        System.out.println("Remaining Donuts: " + remainingDonuts);
        System.out.println("Donuts per student (double): " + donutsPerStudentDouble);
    }
}
