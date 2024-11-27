package entities;

import java.util.ArrayList;
import java.util.List;

public class Location {
    private int x;                          // Координата X
    private int y;                          // Координата Y
    private List<Animal> animals;           // Список животных в клетке
    private List<Plant> plants;             // Список растений в клетке

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
        this.animals = new ArrayList<>();
        this.plants = new ArrayList<>();
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void displayLocation() {
        System.out.print("[");
        if (animals.isEmpty()) {
            System.out.print("  ");
        } else {
            System.out.print(animals.size() + "A");
        }
        if (plants.isEmpty()) {
            System.out.print("  ");
        } else {
            System.out.print(plants.size() + "P");
        }
        System.out.print("] ");
    }
}
