package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void fight_playerWins() {
        Character player = new Character("Player", 100, 50, 10);
        Character enemy = new Character("Enemy", 50, 30, 5);

        Main.conductBattle(player, enemy);

        assertTrue(player.isAlive(), "Player should survive the fight.");
        assertFalse(enemy.isAlive(), "Enemy should die after the fight.");
    }

    @Test
    void fight_enemyWins() {
        Character player = new Character("Player", 50, 30, 5);
        Character enemy = new Character("Enemy", 100, 50, 10);

        Main.conductBattle(player,  enemy);

        assertFalse(player.isAlive(), "Player should die in the fight.");
        assertTrue(enemy.isAlive(), "Enemy should survive the fight.");
    }

}