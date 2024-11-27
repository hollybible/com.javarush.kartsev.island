package Predators;

import entities.Animal;
import entities.Location;
import herbivores.Herbivore;

public abstract class Predator extends Animal {

    public Predator(double weight, int maxSpeed, double maxSatiety, double actualSatiety, Location location) {
        super(weight, maxSpeed, maxSatiety, actualSatiety, location);
    }
    @Override
    public void eat(Object food) {
        if (food instanceof Herbivore) {
            Herbivore herbivore = (Herbivore) food;
            updateSatiety(herbivore.getWeight() / 10);  // Хищник увеличивает сытость, съедая травоядное
            herbivore.die();  // Травоядное умирает
        }
    }
    @Override
    public void reproduce() {
        // Логика размножения для хищников
    }

    @Override
    public void die() {
        if (actualSatiety <= 0) {
            System.out.println("умирает от голода.");
        }
    }
}









//    public static class Predator extends Animal {
//
//    }
//
//    public static class Bear extends Predator {
//    }
//
//    public static class Boa extends Predator {
//    }
//
//    public static class Eagle extends Predator {
//    }
//
//    public static class Fox extends Predator {
//    }
//
//    public static class Wolf extends Predator {
//    }
