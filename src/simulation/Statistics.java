package simulation;

import entities.Island;
import entities.Location;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Statistics {
    public static void printIslandState(Island island) {
        Map<String, Integer> globalCounts = new ConcurrentHashMap<>();
        
        // Сбор статистики по всем локациям
        for (int i = 0; i < island.getWidth(); i++) {
            for (int j = 0; j < island.getHeight(); j++) {
                Location location = island.getLocation(i, j);
                Map<String, Integer> localCounts = location.getPopulationStatistics();

                // Добавляем данные локальной статистики к глобальной
                for (Map.Entry<String, Integer> entry : localCounts.entrySet()) {
                    globalCounts.merge(entry.getKey(), entry.getValue(), Integer::sum);
                }
            }
        }

        // Вывод глобальной статистики
        System.out.println("=== Состояние острова ===");
        globalCounts.forEach((key, value) -> System.out.printf("%s: %d%n", key, value));
        System.out.println("=========================");
    }
}
