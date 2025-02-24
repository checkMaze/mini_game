package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final int MIN_ENEMIES = 3;
    private static final int MAX_ENEMIES = 13;
    private static final int MIN_HEALTH = 10;
    private static final int MAX_HEALTH = 100;
    private static final int MIN_STRENGTH = 25;
    private static final int MAX_STRENGTH = 75;
    private static final int MIN_SPEED = 3;
    private static final int MAX_SPEED = 10;
    private static final int MIN_WINRATE = 10;
    private static final int MAX_WINRATE = 90;
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {

        // Generiere Gegner Anzahl
        int numberOfEnemies = getRandomValue(MIN_ENEMIES, MAX_ENEMIES);
        System.out.println("You will face " + numberOfEnemies + " enemies!");

        // Generiere Gegner Stats
        Character enemy = createRandomCharacter();
        System.out.println("Enemy stats:");
        enemy.printStatus();

        // Ziel-Winrate
        int targetWinRate = getRandomValue(MIN_WINRATE, MAX_WINRATE);
        System.out.println("Try to achieve a winrate of " + targetWinRate + "%");

        // Spieler-Eingabe
        Character player = createPlayerCharacter();

        // Kampf-Logik
        int wins = simulateFights(player, enemy, numberOfEnemies);

        // Ergebnisse anzeigen
        displayResults(wins, numberOfEnemies, targetWinRate);
    }

    public static Character createRandomCharacter() {
        int health = getRandomValue(MIN_HEALTH, MAX_HEALTH);
        int strength = getRandomValue(MIN_STRENGTH, MAX_STRENGTH);
        int speed = getRandomValue(MIN_SPEED, MAX_SPEED);
        return new Character("Enemy", health, strength, speed);
    }

    public static Character createPlayerCharacter() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your stats:");
        System.out.print("Health: ");
        int health = Integer.parseInt(scanner.nextLine());
        System.out.print("Strength: ");
        int strength = Integer.parseInt(scanner.nextLine());
        System.out.print("Speed: ");
            int speed = Integer.parseInt(scanner.nextLine());
        return new Character("Player", health, strength, speed);
    }

    public static int simulateFights(Character player, Character enemy, int totalEnemies) {
        int wins = 0;

        System.out.println("Battles start!");
        for (int i = 0; i < totalEnemies; i++) {
            System.out.println("\n--- Battle " + (i + 1) + " ---");
            boolean playerWon = conductBattle(player, enemy);
            if (playerWon) {
                wins++;
            }
            // Lebenspunkte zurücksetzen
            player.resetHealth();
            enemy.resetHealth();
        }

        return wins;
    }

    public static boolean conductBattle(Character player, Character enemy) {

        while (player.isAlive() && enemy.isAlive()) {
            if (player.getSpeed() >= enemy.getSpeed()) {
                performAttack(player, enemy);
                if (enemy.isAlive()) {
                    performAttack(enemy, player);
                }
            } else {
                performAttack(enemy, player);
                if (player.isAlive()) {
                    performAttack(player, enemy);
                }
            }
        }

        boolean playerWon = player.isAlive();
        System.out.println(playerWon ? "Player wins!" : "Enemy wins!");

        return playerWon;
    }

    public static void performAttack(Character attacker, Character defender) {
        System.out.println(attacker.getName() + " attacks " + defender.getName() + "!");
        int damage = attacker.calculateAttackDamage();
        defender.takeDamage(damage);
        System.out.println(defender.getName() + " takes " + damage + " damage. Remaining health: " + defender.getHealth());
    }

    public static void displayResults(int wins, int totalBattles, int targetWinRate) {
        System.out.println("\n------ Results ------");
        System.out.println("You won " + wins + " out of " + totalBattles + " battles.");
        double actualWinRate = ((double) wins / totalBattles) * 100;
        int roundedWinRate = (int) Math.round(actualWinRate);
        System.out.println("Your winrate is: " + roundedWinRate + "%");
        System.out.println("The difference from the target winrate (" + targetWinRate + "%) is: " + (roundedWinRate - targetWinRate) + "%");
    }

    public static int getRandomValue(int min, int max) {
        return RANDOM.nextInt(max - min + 1) + min;
    }
}
