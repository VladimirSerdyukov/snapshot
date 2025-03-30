package org.example.filter;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class FilterCustom implements Filter {

    @Override
    public Object apply(Object o) {
        /*
        Для фильтрации необходима конкретизация вложенного типа т.к от этого зависит сам метод
        фильтрации
        */
        if(o instanceof Collection<?>) {
            o = ((Collection<String>) o).stream()
                    .filter(s -> s.length() > 3)
                    .toList();
        } else if (o instanceof Map<?,?>){
            o = ((Map<String, String>) o).entrySet().stream()
                    .filter(s -> s.getValue().length() > 4)
                    .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
        } else {
            System.out.println("Объект не является списком!");
        }
        return o;
    }
}
