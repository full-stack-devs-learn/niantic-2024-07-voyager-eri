package com.niantic;

public class Dagger extends Weapon
{
    private int daggerCount;

    public Dagger(String name, int damage)
    {
        super(name, damage);
        daggerCount = 2;
    }

    @Override
    public int attack()
    {
        if(daggerCount > 0)
        {
            percentCharged += (percentCharged <= 80) ? 20 : 0;
            return getDamage();
        }
        return 0;
    }

    @Override
    public int powerAttack()
    {
        if(percentCharged == 100 && daggerCount > 0)
        {
            daggerCount--;
            return getDamage() * 3;
        }
        return 0;
    }

    @Override
    public int getRange() {
        return 10;
    }

    public void addDagger()
    {
        daggerCount++;
    }

    public int getDaggerCount()
    {
        return daggerCount;
    }
}
