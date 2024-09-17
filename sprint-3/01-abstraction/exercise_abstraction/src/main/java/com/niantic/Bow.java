package com.niantic;

import java.util.Timer;
import java.util.TimerTask;

public class Bow extends Weapon
{
    private String arrowType;
    private int quiverSize;
    private int arrowCount;
    private boolean isUnlimited = false;
    private int powerAttackMultiplier = 1;

    public Bow(String name, int damage, String arrowType, int quiverSize)
    {
        super(name, damage);
        this.arrowType = arrowType;
        this.quiverSize = quiverSize;
        arrowCount = quiverSize;

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(percentCharged <= 80)
                {
                    percentCharged += 20;
                }
            }
        }, 2000);
    }

    @Override
    public int attack()
    {
        if(arrowCount != 0)
        {
            System.out.println("attacked!");
            powerAttackMultiplier = 1;

            if(!isUnlimited)
            {
                arrowCount--;
                powerAttackMultiplier = 2;
            }

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run()
                {
                    if(arrowCount < quiverSize )
                    {
                        arrowCount++;
                    }
                }
            }, 5000);

            return switch (arrowType)
            {
                case "standard" -> getDamage() * powerAttackMultiplier;
                case "poison" -> getDamage() * 2 * powerAttackMultiplier;
                case "explosive" -> getDamage() * 3 * powerAttackMultiplier;
                default -> 0;
            };
        }
        return 0;
    }

    @Override
    public int powerAttack()
    {
        isUnlimited = true;

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                isUnlimited = false;
            }
        }, 5000);

        return 0;
    }

    @Override
    public int getRange() {
        return 20;
    }

    public int getArrowCount()
    {
        return arrowCount;
    }
}
