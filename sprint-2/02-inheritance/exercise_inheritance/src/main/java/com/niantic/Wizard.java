package com.niantic;

public class Wizard extends Character
{
    private int mana;

    public Wizard(String name, int health, int level, int experience, int mana)
    {
        super(name, health, level, experience);
        this.mana = mana;
    }

    public int getMana()
    {
        return mana;
    }

    public void castSpell(Character target)
    {
        if (isDefeated())
        {
            System.out.println(this.name + " has been defeated and cannot attack another player.");
            return;
        }

        System.out.println(this.name + " attacks " + target.getName());

        if(mana >= 10)
        {
            target.takeDamage(attackDamage * 2);
            mana -= 10;
        }
    }

    public void regenerateMana(int amount)
    {
        if(!isDefeated())
        {
            mana += amount;
        }
    }

    @Override
    public void levelUp()
    {
        super.levelUp();
        if(!isDefeated()) regenerateMana(10);
    }

    @Override
    public String specialAbility()
    {
        return "Cast Spells";
    }
}
