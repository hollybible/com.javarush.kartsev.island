package Main;

import entities.Animal;
import entities.AnimalType;
import entities.Island;
import entities.Location;
import factory.AnimalFactory;
import simulation.Statistics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

    public static void main(String[] args) {
        Island island = new Island(100, 100);
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // Добавление животных на остров
        Location location = island.getLocation(0, 0);
        location.addAnimal(AnimalFactory.createAnimal(AnimalType.WOLF));
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
                    Thread.sleep(1000); // Рост растений каждые 1 секунду
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

    }
}
