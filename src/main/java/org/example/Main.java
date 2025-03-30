package org.example;

import org.example.filter.FilterCustom;
import org.example.filter.Task2;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Main {
    /*
    Рассчитываю что под массивом в данном задании всё-таки имеется ввиду список.

     */
    public static void main(String[] args) {
        Task2 task = new Task2();

        Collection<?> list = task.getList();
        System.out.println("Перед фильтрацией: ");
        list.forEach(System.out::println);
        List<?> responceList = (List<?>) task.filter(list, new FilterCustom());
        System.out.println("\nПосле фильтрации: ");
        responceList.forEach(System.out::println);

        Map<?, ?> map = task.getMap();
        System.out.println("\nПеред фильтрацией: ");
        map.entrySet().forEach(System.out::println);
        Map<?, ?> responce = (Map<?, ?>) task.filter(map, new FilterCustom());
        System.out.println("\nПосле фильтрации: ");
        responce.entrySet().forEach(System.out::println);


    }
}