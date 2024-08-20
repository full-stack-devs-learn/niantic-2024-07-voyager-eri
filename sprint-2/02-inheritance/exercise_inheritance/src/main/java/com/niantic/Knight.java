package com.niantic;

public class Knight extends Character
{
    private int armor;

    public Knight(String name, int health, int level, int experience, int armor)
    {
        super(name, health, level, experience);
        this.armor = armor;
    }

    public int getArmor()
    {
        return armor;
    }

    @Override
    public void takeDamage(int damage)
    {
        int reducedDamage;

        if(damage > armor)
        {
            reducedDamage = damage - armor;
            super.takeDamage(reducedDamage);
        }
    }

    @Override
    public void levelUp()
    {
        super.levelUp();
        if(!isDefeated()) armor += 5;
    }

    @Override
    public String specialAbility()
    {
        return "Armor Shield";
    }
}
