package herbivores;

import entities.Animal;
import entities.Location;
import entities.Plant;

public abstract class Herbivore extends Animal {

    public Herbivore(double weight, int maxSpeed, double maxSatiety, double actualSatiety, Location location) {
        super(weight, maxSpeed, maxSatiety, actualSatiety, location);
    }

    @Override
    public void eat(Object food) {
        if (food instanceof Plant) {
            Plant plant = (Plant) food;
            updateSatiety(plant.getNutritionalValue());  // Увеличиваем сытость на основе питания растениями
            plant.die();  // Растение погибает после того как съедено
        }
    }

    @Override
    public void reproduce() {
        // Логика размножения для травоядных
    }

    @Override
    public void die() {
        if (actualSatiety <= 0) {
            System.out.println("умирает от голода.");
        }
    }
}
