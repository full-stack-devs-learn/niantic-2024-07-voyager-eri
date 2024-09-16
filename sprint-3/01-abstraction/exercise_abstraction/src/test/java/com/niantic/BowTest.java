package com.niantic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BowTest
{
    private Bow bow;
    private String name = "Bowy";
    private int damage = 10;
    private String arrowType = "poison";
    private int quiverSize = 100;

    @BeforeEach
    public void setup()
    {
        bow = new Bow(name, damage, arrowType, quiverSize);
    }
}