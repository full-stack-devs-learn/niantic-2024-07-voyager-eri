package com.niantic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest extends ReflectionBase<Knight>
{
    private Knight knight;
    private String name = "Meta Knight";
    private int health = 100;
    private int level = 1;
    private int experience = 0;
    private int armor = 10;

    @BeforeEach
    public void setup()
    {
        knight = new Knight(name, health, level, experience, armor);
    }

    @Test
    public void takeDamage_shouldReduceDamage_thatIsDealtToKnight()
    {
        // ARRANGE
        int damagePoints = 20;
        int expectedHealth = 90;

        // ACT
        knight.takeDamage(damagePoints);
        int actualHealth = knight.getHealth();

        // ASSERT
        assertEquals(expectedHealth, actualHealth, "Health: 100 - Armor: 10 - Attack damage: 20 - Knight should have taken 10 damage in total");
    }

    @Test
    public void takeDamage_shouldNotReduceHealth_ifArmorIsGreaterThanTheAttackDamage()
    {
        // ARRANGE
        int damagePoints = 5;
        int expectedHealth = 100;

        // ACT
        knight.takeDamage(damagePoints);
        int actualHealth = knight.getHealth();

        // ASSERT
        assertEquals(expectedHealth,actualHealth, "Health: 100 - Armor: 10 - Attack damage: 5 - Knight should not have taken any damage");
    }

    @Test
    public void takeDamage_shouldNotReduceArmor()
    {
        // ARRANGE
        int damagePoints = 5;
        int expectedArmor = armor;

        // ACT
        knight.takeDamage(damagePoints);
        int actualArmor = knight.getArmor();

        // ASSERT
        assertEquals(expectedArmor, actualArmor, "Because armor should not decrease");
    }

    @Test
    public void levelUp_shouldIncrease_healthLevelAndArmor()
    {
        // ARRANGE
        int expectedHealth = 110;
        int expectedLevel = 2;
        int expectedArmor = 15;

        // ACT
        knight.levelUp();

        // ASSERT
        int actualHealth = knight.getHealth();
        int actualLevel = knight.getLevel();
        int actualArmor = knight.getArmor();

        assertEquals(expectedHealth, actualHealth, "Because the knight leveled up which should have added health");
        assertEquals(expectedLevel, actualLevel, "Because the knight leveled up");
        assertEquals(expectedArmor, actualArmor, "Because the wizard leveled up which should have added armor");
    }
}