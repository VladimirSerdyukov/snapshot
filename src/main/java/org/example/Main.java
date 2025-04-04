package org.example;

import org.example.streamApi.FactorialTask;
import org.example.streamApi.Order;
import org.example.streamApi.StreamCollectorsExample;
import org.example.streamApi.Student;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

/*
* Предположим, у нас есть список заказов, и каждый заказ представляет
*  собой продукт и его стоимость. Задача состоит в использовании Stream API
*  и коллекторов для решения следующих задач:

1. Создайте список заказов с разными продуктами и их стоимостями.
2. Группируйте заказы по продуктам.
3. Для каждого продукта найдите общую стоимость всех заказов.
4. Отсортируйте продукты по убыванию общей стоимости.
5. Выберите три самых дорогих продукта.
6. Выведите результат: список трех самых дорогих продуктов и их общая стоимость.*/
public class Main {
    public static void main(String[] args) {
        List<Order> list = StreamCollectorsExample.getCollect(); //

        Map<String, List<Order>> groupByproduct = list.stream().collect(Collectors.groupingBy(Order::getProduct));
        System.out.println("Группируйте заказы по продуктам:");
        groupByproduct.forEach((key, value) -> {
            System.out.println(key);
            value.forEach(System.out::println);
        });

        System.out.println("Для каждого продукта найдите общую стоимость всех заказов:");
        Map<String, Double> sumPrice = list.stream()
                .collect(Collectors.groupingBy(Order::getProduct, Collectors.
                        summingDouble((o) -> o.getCost())));
        sumPrice.forEach((key, value) -> System.out.println(key + ": " + value));


        System.out.println("Отсортируйте продукты по убыванию общей стоимости. v1");
        sumPrice.entrySet().stream().sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())).forEach(System.out::println);
        System.out.println("Отсортируйте продукты по убыванию общей стоимости. v2");
        list.stream().collect(Collectors.collectingAndThen(Collectors
                        .groupingBy(Order::getProduct, Collectors.summingDouble((o) -> o.getCost())),
                map -> map.entrySet().stream()
                        .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                        .collect(Collectors.toList()))).forEach((entry) -> System.out.println(entry.getKey() + ": " + entry.getValue()));


        System.out.println("Выберите три самых дорогих продукта");

        list.stream()
                .collect(Collectors.groupingBy(Order::getCost,
                        Collectors.maxBy((o1, o2) -> Double.compare(o1.getCost(), o2.getCost()))))
                .entrySet().stream().limit(3).forEach(o -> System.out.println(
                        (o.getValue().orElse(new Order("none", 0.0)).getProduct()) + " " +
                                (o.getValue().orElse(new Order("none", 0.0)).getCost())));

        System.out.println("Выведите результат: список трех самых дорогих продуктов и их общая стоимость");

        Double x = list.stream()
                .collect(Collectors.groupingBy(Order::getCost,
                    Collectors.maxBy((o1, o2) -> Double.compare(o1.getCost(), o2.getCost()))))
                .entrySet().stream().limit(3)
                .peek(o -> o.getValue().ifPresent(j -> System.out.println(j.getProduct() + " -- " + j.getCost())))
                .map(o -> o.getValue().orElse(new Order("none", 0)).getCost())
                .reduce(0.0, Double::sum);
        System.out.println(x);

        /*
          1. Создайте коллекцию студентов, где каждый студент содержит информацию о
             предметах, которые он изучает, и его оценках по этим предметам.
          2. Используйте Parallel Stream для обработки данных и создания Map,
             где ключ - предмет, а значение - средняя оценка по всем студентам.
          3. Выведите результат: общую Map с средними оценками по всем предметам.
        */


        List<Student> students = Arrays.asList(
                new Student("Student1", Map.of("Math", 90, "Physics", 85)),
                new Student("Student2", Map.of("Math", 95, "Physics", 88)),
                new Student("Student3", Map.of("Math", 88, "Chemistry", 92)),
                new Student("Student4", Map.of("Physics", 78, "Chemistry", 85))
        );

        System.out.println("Работа со студентами: ");
        Map<String, Double> map = students.parallelStream().flatMap(j -> j.getGrades().entrySet().stream())
                .collect(Collectors.groupingBy(e-> e.getKey(), Collectors.averagingLong(e -> e.getValue())));
        map.entrySet().forEach(System.out::println);
        /*
        * 1. Реализуйте класс FactorialTask, который расширяет RecursiveTask.
             Этот класс будет выполнять рекурсивное вычисление факториала числа.
          2. В конструкторе FactorialTask передайте число n, факториал которого
             нужно вычислить.
          3. В методе compute() разбейте задачу на подзадачи и используйте
             fork() для их асинхронного выполнения.
          4. Используйте join() для получения результатов подзадач и
             комбинирования их для получения общего результата.
          5. В основном методе создайте экземпляр FactorialTask с числом,
             для которого нужно вычислить факториал, и запустите его в ForkJoinPool.
               Выведите результат вычисления факториала.*/

        int n = 10; // Вычисление факториала для числа 10

        System.out.println("Вычисление факториала: ");

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FactorialTask factorialTask = new FactorialTask(n);

        long result = forkJoinPool.invoke(factorialTask);

        System.out.println("Факториал " + n + "! = " + result);
    }
}


