package org.example;


import org.example.iterator.ListInTheMap;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ListInTheMap s = new ListInTheMap();

        Map<String, Integer> d = s.createMap();

        d.entrySet().forEach(System.out::println);
    }

}