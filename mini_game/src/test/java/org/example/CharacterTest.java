package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {

    @Test
    void calculateAttackDamage() {
        Character character = new Character("Test", 100, 50, 10);

        int damage = character.calculateAttackDamage();

        assertTrue(damage >= 1 && damage <= 50, "Damage should be between 1 and the character's strength.");
    }

    @Test
    void takeDamage() {
        Character character = new Character("Test", 100, 50, 10);

        character.takeDamage(30);

        assertEquals(70, character.getHealth(), "Health should decrease correctly after taking damage.");

        character.takeDamage(80);

        assertEquals(0, character.getHealth(), "Health should not drop below 0.");
    }

    @Test
    void resetHealth() {
        Character character = new Character("Test", 100, 50, 10);

        character.takeDamage(30);
        assertEquals(70, character.getHealth());

        character.resetHealth(); // Reset health manually
        assertEquals(100, character.getHealth(), "Health should be reset to the original value.");
    }

    @Test
    void isAlive() {
        Character character = new Character("Test", 100, 50, 10);

        assertTrue(character.isAlive(), "Character should be alive initially.");

        character.takeDamage(100);

        assertFalse(character.isAlive(), "Character should be dead if health is 0 or less.");
    }
}
