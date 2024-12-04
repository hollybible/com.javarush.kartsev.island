package entities;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Location {
    private final List<Animal> animals = new CopyOnWriteArrayList<>();
    private double plants = 5.0;

    public void addAnimal(Animal animal) {
        if (animals.size() < animal.getType().getMaxOnCell()) {
            animals.add(animal);
        }
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public double getPlants() {
        return plants;
    }

    public void growPlants() {
        plants += Math.random() * 10;
    }

    public void consumePlants(double amount) {
        plants = Math.max(0, plants - amount);
    }

    public synchronized Map<String, Integer> getPopulationStatistics() {
        Map<String, Integer> statistics = new ConcurrentHashMap<>();

        // Подсчёт животных по типам
        for (Animal animal : animals) {
            statistics.merge(animal.getType().name(), 1, Integer::sum);
        }

        // Добавляем количество растений
        statistics.put("Plants", (int) plants);

        return statistics;
    }
}
