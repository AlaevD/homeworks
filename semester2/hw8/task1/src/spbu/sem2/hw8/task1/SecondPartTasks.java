package spbu.sem2.hw8.task1;

import javafx.util.Pair;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class SecondPartTasks {

    private SecondPartTasks() {}

    // Найти строки из переданных файлов, в которых встречается указанная подстрока.
    public static List<String> findQuotes(List<String> paths, CharSequence sequence) {
        return paths.stream().flatMap(path ->  {
            try {
                return Files.lines(Paths.get(path)).filter(str -> str.contains(sequence))
                            .collect(Collectors.toList()).stream();
            } catch (IOException e) {
                return Stream.empty();
            }
        }).collect(Collectors.toList());
    }

    // В квадрат с длиной стороны 1 вписана мишень.
    // Стрелок атакует мишень и каждый раз попадает в произвольную точку квадрата.
    // Надо промоделировать этот процесс с помощью класса java.util.Random и посчитать, какова вероятность попасть в мишень.
    public static double piDividedBy4() {
        int totalAttempts = 1234321;
        double r = 0.5;
        Random rng = new Random();
        return (double)Stream
                .generate(() -> (Math.pow(rng.nextDouble() - r, 2) + Math.pow(rng.nextDouble() - r, 2)))
                .limit(totalAttempts).filter((d) -> d <= Math.pow(r, 2))
                .count() / totalAttempts;
    }

    // Дано отображение из имени автора в список с содержанием его произведений.
    // Надо вычислить, чья общая длина произведений наибольшая.
    public static String findPrinter(Map<String, List<String>> compositions) {
        return compositions.entrySet().stream().map(r -> new Pair<>(r.getKey(), r.getValue()
                                                                                 .stream()
                                                                                 .mapToInt(String::length)
                                                                                 .sum()))
                                               .max(Comparator.comparing(Pair::getValue))
                                               .orElse(new Pair<>(null, null)).getKey();
    }

    // Вы крупный поставщик продуктов. Каждая торговая сеть делает вам заказ в виде Map<Товар, Количество>.
    // Необходимо вычислить, какой товар и в каком количестве надо поставить.
    public static Map<String, Integer> calculateGlobalOrder(List<Map<String, Integer>> orders) {
        return orders.stream()
                .flatMap(order -> order.entrySet().stream())
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.collectingAndThen(Collectors.toList(),
                        list -> list.stream().mapToInt(Map.Entry::getValue).sum())));
    }
}
