package org.example.streamApi;

import java.util.List;

public class StreamCollectorsExample {
    public static List<Order> getCollect() {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );
        return orders;
    }
}
