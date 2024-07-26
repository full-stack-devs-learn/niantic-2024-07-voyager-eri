package com.nianatic;

public class ExercisesChallenge
{

    /*
     * Given an array of numbers, add only
     * the first 2 numbers and return
     * the answer.
     *
     * The array may have fewer than 2 numbers,
     * so you will need to have logic to deal
     * with that.
     *
     * sumFirst2Numbers([1, 2, 3])       =>  3
     * sumFirst2Numbers([10, 8, 14, 32]) =>  18
     * sumFirst2Numbers([3])             =>  3
     * sumFirst2Numbers([])              =>  0
     */
    public int sumFirst2Numbers(int[] numbers)
    {
        if (numbers.length >= 2)
        {
            return numbers[0] + numbers[1];
        }
        else if (numbers.length == 1)
        {
            return numbers[0];
        }
        else if (numbers.length ==0)
        {
            return 0;
        }

        return -1;
    }

    /*
     * Given an array of names, create a
     * new array of the same size. The new
     * array should have all of the names
     * in it but in reverse order.
     *
     * reverseNames(["Ron", "Lisa", "Kent", "Michelle"]) => ["Michelle", "Kent", "Lisa", "Ron"]
     */
    public String[] reverseNames(String[] names)
    {
        String[] reversedNames = new String[names.length];
        int reversedArrayCounter = 0;

        for (int i = names.length - 1; i > -1; i--)
        {
            reversedNames[reversedArrayCounter] = names[i];
            reversedArrayCounter++;
        }
        return reversedNames;
    }



    /*
     * Given a multidimensional array of numbers,
     * combine all numbers into a single 1 dimensional array
     *
     * The nested arrays will all have numbers
     * but the amount can vary
     *
     * combineArrays([
     *                [1,2,3],
     *                [4,5,6],
     *                [7,8,9]
     *               ])         =>  [1,2,3,4,5,6,7,8,9]
     *
     * combineArrays([
     *                [1,2],
     *                [3,4,5,6],
     *                [7,8,9]
     *               ])         =>  [1,2,3,4,5,6,7,8,9]
     *
     * combineArrays([
     *                [1,2,3,4],
     *                [5,6,7,8,9]
     *               ])         =>  [1,2,3,4,5,6,7,8,9]
     */
    public int[] combineArrays(int[][] numbers)
    {
        int arrayCellTotal = 0;
        int i = 0;

        for (int row = 0; row < numbers.length; row++)
        {
            for (int col = 0; col < numbers[row].length; col++)
            {
                arrayCellTotal++;
            }
        }

        int [] combinedArray = new int[arrayCellTotal];


        for (int row = 0; row < numbers.length; row++)
        {
            for (int col = 0; col < numbers[row].length; col++)
            {
                combinedArray[i] = numbers[row][col];
                i++;
            }
        }

        return combinedArray;
    }
}
