package com.niantic;

public class Sword extends Weapon
{
    public Sword(String name, int damage)
    {
        super(name, damage);
    }

   @Override
   public int attack()
   {
       if(percentCharged <= 90)
       {
           percentCharged += 10;
       }
       return getDamage();
   }

   @Override
    public int powerAttack()
   {
       if(percentCharged < 50)
       {
           return getDamage();
       }
       else if(percentCharged <= 90)
       {
           percentCharged -= 50;
           return getDamage() * 2;
       }
       else
       {
           percentCharged = 0;
           return getDamage() * 4;
       }
   }

   @Override
    public int getRange()
   {
       return 1;
   }
}
