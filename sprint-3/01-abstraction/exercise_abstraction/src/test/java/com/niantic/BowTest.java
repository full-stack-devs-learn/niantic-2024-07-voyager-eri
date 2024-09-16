package com.niantic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BowTest
{
    private Bow bow;
    private String name = "Bowy";
    private int damage = 10;
    private String arrowType = "standard";
    private int quiverSize = 4;

    @BeforeEach
    public void setup()
    {
        bow = new Bow(name, damage, arrowType, quiverSize);
    }

    @Test
    public void attack()
    {
        bow.setPercentCharged(100);
        bow.powerAttack();
        bow.attack();
        bow.attack();
        bow.attack();
        bow.attack();
        bow.attack();
        bow.attack();
        bow.attack();
        bow.attack();


    }
}