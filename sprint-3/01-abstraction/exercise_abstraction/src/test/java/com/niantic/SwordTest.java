package com.niantic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwordTest
{
    private Sword sword;
    private String name = "Slashy";
    private int damage = 10;

    @BeforeEach
    public void setup()
    {
        sword = new Sword(name, damage);
    }

    @Test
    public void attack_shouldDealDefaultDamageToOtherCharacter()
    {
        // arrange
        int expectedDamage = 10;

        //act
        int actualDamage = sword.attack();

        //assert
        assertEquals(expectedDamage, actualDamage, "Because the Sword's set damage is 10.");
    }

    @Test
    public void attack_shouldIncreasePercentCharged_By10()
    {
        // arrange
        int expectedCharge = 20;

        // act
        sword.attack();
        sword.attack();

        int actualCharge = sword.getPercentCharged();

        // assert
        assertEquals(expectedCharge, actualCharge, "Because weapon attacked 2 times, should be 20%.");
    }

    @Test
    public void attack_shouldNotIncreasePercentCharged_Beyond100()
    {
        // arrange
        int expectedCharge = 100;

        // act
        sword.setPercentCharged(100);
        sword.attack();

        int actualCharge = sword.getPercentCharged();

        // assert
        assertEquals(expectedCharge, actualCharge, "Because percentCharge should not increase beyond 100%");
    }

    @Test
    public void powerAttack_shouldPerformRegularAttack_ifPercentChargeUnder50()
    {
        // arrange
        int expectedDamage = 10;

        // act
        int actualDamage = sword.powerAttack();

        // assert
        assertEquals(expectedDamage, actualDamage, "percentCharge was 0, so powerAttack should perform regular attack with damage 10");
    }

    @Test
    public void powerAttack_shouldDeliverDoubleDamage_ifPercentCharge50AboveAnd90Under()
    {
        // arrange
        int expectedDamage = 20;
        sword.setPercentCharged(50);

        // act
        int actualDamage = sword.powerAttack();

        // assert
        assertEquals(expectedDamage, actualDamage, "percentCharge was 50, so powerAttack should deal 20 damage");
    }

    @Test
    public void powerAttack_shouldReducePercentChargeBy50_whenPercentCharge50Above()
    {
        // arrange
        int expectedPercent = 0;
        sword.setPercentCharged(50);

        // act
        sword.powerAttack();
        int actualPercent = sword.getPercentCharged();

        // assert
        assertEquals(expectedPercent, actualPercent, "percentCharge before powerAttack: 50 - percentCharge after attack should be 0");
    }

    @Test
    public void powerAttack_shouldDeliverQuadrupleDamage_ifPercentChargeIs100()
    {
        // arrange
        int expectedDamage = 40;
        sword.setPercentCharged(100);

        // act
        int actualDamage = sword.powerAttack();

        // assert
        assertEquals(expectedDamage, actualDamage, "Because percentCharged was 100, so it should have performed 4x damage attack");
    }

    @Test
    public void powerAttack_shouldReducePercentChargeToZero_whenPercentCharge100()
    {
        // arrange
        int expectedCharge = 0;
        sword.setPercentCharged(100);

        // act
        sword.powerAttack();
        int actualCharge = sword.getPercentCharged();

        // assert
        assertEquals(expectedCharge, actualCharge, "Because using powerAttack at 100% charge should reduce percentCharge to 0");
    }
}