package entities;

import java.util.List;

public class Predator extends Animal {

    public Predator(AnimalType type) {
        super(type);
    }

    @Override
    public void eat(Location location) {
        System.out.println(type.name() + " is hunting...");
        // Логика для охоты, например, если рядом травоядное животное — съесть его
        for (Animal animal : location.getAnimals()) {
            if (animal instanceof Herbivore) {
                if (Math.random() < 0.6) { // 60% шанс на поедание
                    location.removeAnimal(animal);
                    this.decreaseHunger(animal.getWeight());
                    System.out.println(type.name() + " ate a " + animal.getType().name());
                    break;
                }
            }
        }
    }

    @Override
    public void move(Island island) {
        System.out.println(type.name() + " is moving...");
        // Перемещение на случайную клетку
        int x = (int) (Math.random() * island.getWidth());
        int y = (int) (Math.random() * island.getHeight());
        Location newLocation = island.getLocation(x, y);
        newLocation.addAnimal(this);
    }

    @Override
    public void reproduce(Location location) {
        System.out.println(type.name() + " is reproducing...");
        if (location.getAnimals().stream().anyMatch(a -> a.getType() == this.type)) {
            location.addAnimal(new Predator(this.type));
        }
    }

    @Override
    public void die() {
        System.out.println(type.name() + " has died.");
    }

    @Override
    public void checkIfStarved() {
        if (hunger >= 100) {
            this.die();
        }
    }
}
