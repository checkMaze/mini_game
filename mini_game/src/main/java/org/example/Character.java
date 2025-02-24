package org.example;

import java.util.Random;

public class Character {

    private final String name;   // Konstant, da sich der Name nicht ändern sollte
    private int health;          // Aktuelle Lebenspunkte
    private final int maxHealth; // Maximale Lebenspunkte
    private final int strength;  // Stärke für Angriffe
    private final int speed;     // Geschwindigkeit für Reihenfolge im Kampf

    private static final Random RANDOM = new Random(); // Vermeidet mehrfaches Erstellen von Random-Instanzen

    // Konstruktor
    public Character(String name, int health, int strength, int speed) {
        this.name = name;
        this.health = health;
        this.maxHealth = health; // Speichert die ursprüngliche Gesundheit
        this.strength = strength;
        this.speed = speed;
    }

    // Angriffsmethode: Berechnet den Schaden basierend auf der Stärke
    public int calculateAttackDamage() {
        return RANDOM.nextInt(strength) + 1; // Schaden zwischen 1 und der Stärke
    }

    // Verteidigungsmethode: Reduziert die Lebenspunkte
    public void takeDamage(int damage) {
        this.health = Math.max(0, this.health - damage); // Verhindert negative Lebenspunkte
    }

    // Lebenspunkte zurücksetzen (z. B. nach einem Kampf)
    public void resetHealth() {
        this.health = maxHealth;
    }

    // Status anzeigen
    public void printStatus() {
        System.out.printf("%s - Health: %d/%d, Strength: %d, Speed: %d%n",
                name, health, maxHealth, strength, speed);
    }

    // Prüfen, ob der Charakter noch lebt
    public boolean isAlive() {
        return this.health > 0;
    }

    // Getter für die Geschwindigkeit
    public int getSpeed() {
        return speed;
    }

    // Getter für den Namen
    public String getName() {
        return name;
    }

    // Getter für die aktuelle Gesundheit
    public int getHealth() {
        return health;
    }

    // Getter für die maximale Gesundheit
    public int getMaxHealth() {
        return maxHealth;
    }

    // Getter für die Stärke
    public int getStrength() {
        return strength;
    }
}
