package factory;

import entities.Animal;
import entities.AnimalType;
import entities.Herbivore;
import entities.Predator;

public class AnimalFactory {
    public static Animal createAnimal(AnimalType type) {
        if (type == AnimalType.WOLF || type == AnimalType.SNAKE || type == AnimalType.FOX || type == AnimalType.BEAR || type == AnimalType.EAGLE) {
            return new Predator(type);
        } else {
            return new Herbivore(type);
        }
    }
}

