package com.niantic;

public class BasicUserOutput
{
    public static void main(String[] args)
    {
        System.out.println("Hello World!");

        int quantity;
        quantity = 150;
        System.out.println(quantity);

        long milestoPluto = Integer.MAX_VALUE;
        System.out.println((milestoPluto));
        milestoPluto = milestoPluto * 2;
        System.out.println(milestoPluto);

        long age = 5000;
        int smallAge = (int)age;
        System.out.println(age + " : " + smallAge);
    }
}
