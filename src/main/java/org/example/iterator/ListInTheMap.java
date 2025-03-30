package org.example.iterator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ListInTheMap {

    List<String> list = new ArrayList<>();


    public ListInTheMap() {
        initList();
    }


    public Map<String, Integer> createMap(List<String> list){
        return list.stream()
                .collect(Collectors.toMap(s -> s, s -> 1, Integer::sum));
    }

    public Map<String, Integer> createMap(){
        return list.stream().collect(Collectors.toMap(s -> s, s -> 1, Integer::sum));
    }


    private void initList(){
        list.add("ACID");
        list.add("JDBC");
        list.add("JPA");
        list.add("HIBERNATE");
        list.add("SPRING");
        list.add("BOOT");
        list.add("SQL");
        list.add("POSTGRESQL");
        list.add("MARINA_DB");
        list.add("CI-CD");
        list.add("ACID");
        list.add("JDBC");
        list.add("JPA");
        list.add("HIBERNATE");
        list.add("POSTGRESQL");
        list.add("MARINA_DB");
        list.add("CI-CD");
        list.add("ACID");
        list.add("JPA");
        list.add("HIBERNATE");
        list.add("SPRING");
        list.add("BOOT");
        list.add("POSTGRESQL");
        list.add("MARINA_DB");
        list.add("CI-CD");
    }
}
