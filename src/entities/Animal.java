package entities;

import java.util.List;

public abstract class Animal {
    protected final AnimalType type;
    protected double weight;
    protected double hunger; // Уровень голода

    public Animal(AnimalType type) {
        this.type = type;
        this.weight = type.getWeight();
        this.hunger = 0;  // Изначально голодное животное
    }

    public AnimalType getType() {
        return type;
    }

    public double getWeight() {
        return weight;
    }

    public double getHunger() {
        return hunger;
    }

    public void increaseHunger(double amount) {
        hunger += amount;
    }

    public void decreaseHunger(double amount) {
        hunger = Math.max(0, hunger - amount);
    }

    public abstract void eat(Location location);

    public abstract void move(Island island);

    public abstract void reproduce(Location location);

    public abstract void die();

    public abstract void checkIfStarved();

}



