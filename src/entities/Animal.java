package entities;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Animal {
    protected double weight;                          //вес
    protected int maxSpeed;                           //(Макс кол-во клеток за раз)
    protected double maxSatiety;                      // максимальная сытость
    protected double actualSatiety;                   //(Фактическая сытость)
    protected Location location;                    // Локация животного на острове
    protected AtomicBoolean isAlive;                // Состояние животного (живое или мертвое)

    public Animal(double weight, int maxSpeed, double maxSatiety, double actualSatiety, Location location) {
        this.weight = weight;
        this.maxSpeed = maxSpeed;
        this.maxSatiety = maxSatiety;
        this.actualSatiety = actualSatiety;
        this.location = location;
        this.isAlive = new AtomicBoolean(true);
    }

    public abstract void eat();                        //args-plant or animal? if there are food in the location

    public abstract void move();                      // put the location


    public abstract void eat(Object food);

    public abstract void reproduce();                 // if we have pair in the location


    public abstract void die();                   //if the animal is too hungry or be eaten (be deleted with garbageCollector)


    // Метод для увеличения сытости
    protected void updateSatiety(double amount) {
        actualSatiety = Math.min(actualSatiety + amount, maxSatiety);
    }

    // Метод для уменьшения сытости
    protected void decreaseSatiety(double amount) {
        actualSatiety = Math.max(actualSatiety - amount, 0);
    }

    // Метод для проверки состояния животного (живо ли оно)
    public boolean isAlive() {
        return isAlive.get();
    }

    public void kill() {
        isAlive.set(false);
        die();
    }

    public double getActualSatiety() {
        return actualSatiety;
    }

    public double getWeight() {
        return weight;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}



