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
    public void attack_shouldReplenishArrow_after5Sec() throws InterruptedException
    {
        // arrange
        int expectedCountBeforeReplenishing = quiverSize - 1;
        int expectedCountAfterReplenishing = quiverSize;

        // act
        bow.attack();

        // assert
        assertEquals(expectedCountBeforeReplenishing, bow.getArrowCount());
        Thread.sleep(5000);
        assertEquals(expectedCountAfterReplenishing, bow.getArrowCount());
    }

    @Test
    public void powerAttack_shouldNotDepleteArrows_whenActive()
    {
        // arrange
        int expectedCount = quiverSize;

        // act
        bow.powerAttack();
        bow.attack();

        // assert
        assertEquals(expectedCount, bow.getArrowCount());
    }
}