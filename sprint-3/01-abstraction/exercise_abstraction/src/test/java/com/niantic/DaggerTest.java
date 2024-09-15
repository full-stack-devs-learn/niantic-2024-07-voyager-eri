package com.niantic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DaggerTest
{
    private Dagger dagger;
    private String name = "Dashy";
    private int damage = 10;

    @BeforeEach
    public void setup()
    {
        dagger = new Dagger(name, damage);
    }

    @Test
    public void attack_shouldDealDamage_ifDaggerCountAboveZero()
    {
        // arrange
        int expectedDamage = 10;

        // act
        int actualDamage = dagger.attack();

        // assert
        assertEquals(expectedDamage, actualDamage, "Because we have a dagger available to use. Should inflict 10 dmg.");
    }

    @Test
    public void attack_shouldIncreasePercentCharged_By20()
    {
        // arrange
        int expectedCharge = 40;

        // act
        dagger.attack();
        dagger.attack();

        int actualCharge = dagger.getPercentCharged();

        // assert
        assertEquals(expectedCharge, actualCharge, "Because we attacked twice, should charge to 40%");
    }

    @Test
    public void attack_shouldNotIncreasePercentCharged_Beyond100()
    {
        // arrange
        int expectedCharge = 100;

        // act
        dagger.setPercentCharged(100);
        dagger.attack();

        int actualCharge = dagger.getPercentCharged();

        // assert
        assertEquals(expectedCharge, actualCharge, "Because percentCharge should not increase beyond 100");
    }

    @Test
    public void powerAttack_shouldPerformAt100PercentCharged()
    {
        // arrange
        int expectedDamage = 30;

        // act
        dagger.setPercentCharged(100);
        int actualDamage = dagger.powerAttack();

        // assert
        assertEquals(expectedDamage, actualDamage, "percentCharge was 100, so should have performed attack with 3x damage");
    }

    @Test
    public void powerAttack_shouldReduceDaggerCountBy1()
    {
        // arrange
        int expectedDaggerCount = 1;

        // act
        dagger.setPercentCharged(100);
        dagger.powerAttack();
        int actualDaggerCount = dagger.getDaggerCount();

        // assert
        assertEquals(expectedDaggerCount, actualDaggerCount, "Because we originally had 2 daggers, should reduce by 1");
    }
}