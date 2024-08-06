package com.niantic.part_3_challenge;

public class Refrigerator
{
    private int currentTemperature;
    private int maxCapacity;
    private int availableCapacity;
    private boolean isDoorOpen;

    public Refrigerator(int currentTemperature, int maxCapacity)
    {
        this.currentTemperature = currentTemperature;
        this.maxCapacity = maxCapacity;
        this.availableCapacity = maxCapacity;
    }

    public int getCurrentTemperature()
    {
        return currentTemperature;
    }

    public int getMaxCapacity()
    {
        return maxCapacity;
    }

    public int getAvailableCapacity()
    {
        return availableCapacity;
    }

    public boolean isDoorOpen()
    {
        return isDoorOpen;
    }

    public void openDoor()
    {
        isDoorOpen = true;
    }

    public void closeDoor()
    {
        isDoorOpen = false;
    }

    public boolean addItem(int capacity)
    {
        if (isDoorOpen && (availableCapacity - capacity > 0))
        {
            availableCapacity -= capacity;
            return true;
        }
        return false;
    }

    public boolean removeItem(int capacity)
    {
        if (isDoorOpen && availableCapacity + capacity < maxCapacity)
        {
            availableCapacity += capacity;
            return true;
        }
        return false;
    }
}
