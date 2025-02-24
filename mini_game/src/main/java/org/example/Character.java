package org.example;

import java.util.Random;

public class Character {
    String name;
    int health;
    int strength;
    int speed;

    // Konstruktor
    public Character(String name, int health, int strength, int speed) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.speed = speed;
    }

    // Angriffsmethode
    public int attack() {
        Random random = new Random();
        return random.nextInt(strength) + 1; // Schaden ist zufällig zwischen 1 und der Stärke
    }

    // Verteidigungsmethode
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) this.health = 0; // Keine negativen Lebenspunkte
    }

    // Status anzeigen
    public void printStatus() {
        System.out.println(name + " - Health: " + health + ", Strength: " + strength + ", Speed: " + speed);
    }

    // Prüfen, ob der Charakter noch lebt
    public boolean isAlive() {
        return this.health > 0;
    }
    public void setHealth(int health) {
        this.health = health;
    }
}
