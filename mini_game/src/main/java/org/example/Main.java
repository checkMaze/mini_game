package org.example;

import java.util.Random;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        int enemies =rand.nextInt(11) + 3;
        System.out.println("You will have " + enemies + " fighters");
        System.out.println("You are meeting enemies with following stats:");
        int enemyHealth = rand.nextInt(91) + 10;
        int enemyStrength = rand.nextInt(51) + 25;
        int enemySpeed = rand.nextInt(8) + 3;
        Character enemy = new Character("Enemy", enemyHealth, enemyStrength, enemySpeed);
        enemy.printStatus();
        int winRate = rand.nextInt(91) + 10;
        System.out.println("Try to have a winrate of "+ winRate +"%");

        System.out.println("What is your strength?");
        int playerStrength = Integer.parseInt(scanner.nextLine());

        System.out.println("What is your health?");
        int playerHealth = Integer.parseInt(scanner.nextLine());

        System.out.println("What is your speed?");
        int playerSpeed = Integer.parseInt(scanner.nextLine());

        int winns = 0;
        Character player = new Character("Player", playerStrength, playerHealth, playerSpeed);
        int leftFights= enemies;

        System.out.println("Battles Start!");
        // Kampf beginnt
        player.printStatus();
        enemy.printStatus();
        while(leftFights != 0) {
            // Kampf-Logik
            while (enemy.isAlive() && player.isAlive()) {
                if (player.speed >= enemy.speed) {
                    takeTurn(player, enemy);
                    if(enemy.isAlive()){
                        takeTurn(enemy, player);
                    }
                }
                else {
                    takeTurn(enemy, player);
                    if (player.isAlive()){
                        takeTurn(player, enemy);
                    }
                }
            }
            // Ergebnis eines Kampfes
            if (player.isAlive()) {
                System.out.println("\n -------------" + player.name + " wins the battle!");
                winns = winns + 1;
                leftFights = leftFights - 1;
                enemy.setHealth(enemyHealth);
                player.setHealth(playerHealth);

            } else {
                System.out.println("\n -------------" + enemy.name + " wins the battle!");
                leftFights = leftFights - 1;
                enemy.setHealth(enemyHealth);
                player.setHealth(playerHealth);
            }

        }
        System.out.println("Your won " + winns + " fighters against " + enemies + " enemies");
        double winrate = ((double) winns / enemies) * 100; // Gleitkommadivision
        int roundedWinrate = (int) Math.round(winrate);
        System.out.println("Your winrate is: " + roundedWinrate + " %");
        System.out.println("The difference is: " + (roundedWinrate - winRate) + " %");
    }

    // Eine Angriff
    public static void takeTurn(Character attacker, Character defender) {
        System.out.println(attacker.name + " attacks " + defender.name + "!");
        int damage = attacker.attack();
        defender.takeDamage(damage);
        System.out.println(defender.name + " takes " + damage + " damage. Remaining health: " + defender.health);
        System.out.println();
    }
}