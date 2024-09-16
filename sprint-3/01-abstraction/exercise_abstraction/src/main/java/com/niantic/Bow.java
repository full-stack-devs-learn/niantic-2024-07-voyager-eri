package com.niantic;

import java.util.Timer;
import java.util.TimerTask;

public class Bow extends Weapon
{
    private String arrowType;
    private int quiverSize;
    private int arrowCount = 10;

    Timer timer = new Timer();

    public Bow(String name, int damage, String arrowType, int quiverSize)
    {
        super(name, damage);
        this.arrowType = arrowType;
        this.quiverSize = quiverSize;

        timer.schedule(new TimerTask() {
            @Override
            public void run()
            {
                if(arrowCount < 10 )
                {
                    arrowCount++;
                }
            }
        }, 5000);

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
            arrowCount--;

            return switch (arrowType)
            {
                case "standard" -> getDamage();
                case "poison" -> getDamage() * 2;
                case "explosive" -> getDamage() * 3;
                default -> 0;
            };
        }
        return 0;
    }

    @Override
    public int powerAttack()
    {
        return 0;
    }

    @Override
    public int getRange() {
        return 20;
    }
}
