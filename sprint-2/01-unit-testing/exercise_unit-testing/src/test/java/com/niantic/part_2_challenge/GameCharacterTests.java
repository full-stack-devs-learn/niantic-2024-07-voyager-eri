package com.niantic.part_2_challenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameCharacterTests
{
    private GameCharacter gameCharacter;
    private int maxEnergyLevel = 100;
    private String name = "Kirby";

    @BeforeEach
    public void setup()
    {
        gameCharacter = new GameCharacter(maxEnergyLevel, name);
    }

    @Test
    public void takeHit_shouldReduceEnergyLevel_byAmountOfEnergySpecified()
    {
        // ARRANGE
        int damagePoints = 25;
        int expectedEnergyLevel = 75;

        // ACT
        gameCharacter.takeHit(damagePoints);
        int actualEnergyLevel = gameCharacter.getEnergyLevel();

        // ASSERT
        assertEquals(expectedEnergyLevel, actualEnergyLevel, "Max energyLevel: 100 - Damage taken: 25 - Should result in 75 energyLevel");
    }

    @Test
    public void takeHit_shouldNotReduceEnergyLevel_BelowZero()
    {
        // ARRANGE
        int damagePoints = 125;
        int expectedEnergyLevel = 0;

        // ACT
        gameCharacter.takeHit(damagePoints);
        int actualEnergyLevel = gameCharacter.getEnergyLevel();

        // ASSERT
        assertEquals(expectedEnergyLevel, actualEnergyLevel, "Because the GameCharacter's energyLevel cannot go below 0");
    }

    @Test
    public void heal_shouldIncreaseEnergyLevel_byAmountOfEnergySpecified()
    {
        // ARRANGE
        int damagePoints = 25;
        int healPoints = 10;
        int expectedEnergyLevel = 85;

        // ACT
        gameCharacter.takeHit(damagePoints);
        gameCharacter.heal(healPoints);
        int actualEnergyLevel = gameCharacter.getEnergyLevel();

        // ASSERT
        assertEquals(expectedEnergyLevel, actualEnergyLevel, "Max energyLevel: 100 - Damage taken: 25 - Heal: 10 - Should result in 85 energyLevel");
    }

    @Test
    public void heal_shouldNotIncreaseEnergyLevel_AboveMaxEnergyLevel()
    {
        // ARRANGE
        int healPoints = 10;
        int expectedEnergyLevel = 100;

        // ACT
        gameCharacter.heal(healPoints);
        int actualEnergyLevel = gameCharacter.getEnergyLevel();

        // ASSERT
        assertEquals(expectedEnergyLevel, actualEnergyLevel, "Because the GameCharacter's energyLevel cannot go above maxEnergyLevel");
    }

    @Test
    public void heal_shouldNotIncreaseEnergyLevel_ifEnergyLevelIsZero()
    {
        // ARRANGE
        int damagePoints = 100;
        int healPoints = 10;
        int expectedEnergyLevel = 0;

        // ACT
        gameCharacter.takeHit(damagePoints);
        gameCharacter.heal(healPoints);
        int actualEnergyLevel = gameCharacter.getEnergyLevel();

        // ASSERT
        assertEquals(expectedEnergyLevel, actualEnergyLevel, "Max energyLevel: 100 - Damage taken: 100 - Heal: 10 - energyLevel is 0, so heal should not happen");
    }

    @Test
    public void isKnockedOut_shouldReturnTrue_ifEnergyLevelIsZero()
    {
        // ARRANGE
        int damagePoints = 100;

        // ACT
        gameCharacter.takeHit(damagePoints);
        boolean isCharacterKnockedOut = gameCharacter.isKnockedOut();

        // ASSERT
        assertTrue(isCharacterKnockedOut, "Max energyLevel: 100 - Damage taken: 100 - Character should be knocked out");
    }

    @Test
    public void isKnockedOut_shouldReturnFalse_ifEnergyLevelIsAboveZero()
    {
        // ARRANGE
        int damagePoints = 50;

        // ACT
        gameCharacter.takeHit(damagePoints);
        boolean isCharacterKnockedOut = gameCharacter.isKnockedOut();

        // ASSERT
        assertFalse(isCharacterKnockedOut, "Max energyLevel: 100 - Damage taken: 50 - Character should not be knocked out");
    }
}