package Main;

import entities.*;
import factory.AnimalFactory;
import simulation.Statistics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

    public static void main(String[] args) {
        Island island = new Island(10, 10);
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // Добавление животных на остров
        Location location = island.getLocation(0, 0);
        location.addAnimal(AnimalFactory.createAnimal(AnimalType.WOLF));
        location.addAnimal(AnimalFactory.createAnimal(AnimalType.BEAR));
        location.addAnimal(AnimalFactory.createAnimal(AnimalType.RABBIT));
        location.addAnimal(AnimalFactory.createAnimal(AnimalType.DEER));
        location.addAnimal(AnimalFactory.createAnimal(AnimalType.HORSE));
        location.addAnimal(AnimalFactory.createAnimal(AnimalType.MOUSE));
        location.addAnimal(AnimalFactory.createAnimal(AnimalType.GOAT));
        location.addAnimal(AnimalFactory.createAnimal(AnimalType.SHEEP));
        location.addAnimal(AnimalFactory.createAnimal(AnimalType.BOAR));
        location.addAnimal(AnimalFactory.createAnimal(AnimalType.BUFFALO));
        location.addAnimal(AnimalFactory.createAnimal(AnimalType.DUCK));
        location.addAnimal(AnimalFactory.createAnimal(AnimalType.CATERPILLAR));

        // Запуск статистики
        executor.submit(() -> {
            while (true) {
                Statistics.printIslandState(island);
                try {
                    Thread.sleep(2000); // Вывод каждые 2 секунду
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Запуск многопоточной симуляции
        for (Animal animal : location.getAnimals()) {
            executor.submit(() -> {
                while (true) {
                    animal.eat(location);
                    animal.move(island);
                    animal.reproduce(location);
                    animal.checkIfStarved();
                    try {
                        Thread.sleep(1000); // Задержка 1 секунда
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }

        // Симуляция роста растений
        executor.submit(() -> {
            while (true) {
                location.growPlants();
                try {
                    Thread.sleep(10000); // Рост растений каждые 10 секунду
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        // Симуляция поедания растений животными
        executor.submit(() -> {
            while (true) {
                // Процесс поедания пищи животными
                for (Location location1 : island.getLocations()) {
                    for (Animal animal : location1.getAnimals()) {
                        if (animal instanceof Herbivore) {  // Только для травоядных животных
                            animal.eat(location1);  // Животное поедает растения
                        }
                    }
                }

                try {
                    Thread.sleep(1000); // Поедание растений каждую секунду
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

    }
}
