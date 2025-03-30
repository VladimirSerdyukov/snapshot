package org.example.filter;

import java.util.*;

public class Task2 {

    ArrayList<String> list = new ArrayList<>();
    Map<String, String> map = new HashMap<>();

    private void jobProcessList() {
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
    }

    private void jobProcessMap() {
        map.put("ACID", "ACID");
        map.put("JPA", "JPA");
        map.put("JDBC", "JDBC");
        map.put("HIBERNATE", "HIBERNATE");
        map.put("SPRING", "SPRING");
        map.put("BOOT", "BOOT");
        map.put("SQL", "SQL");
        map.put("POSTGRESQL", "POSTGRESQL");
        map.put("MARINA_DB", "MARINA_DB");
    }

    public Object filter(Object list, FilterCustom filter) {
        if (list instanceof Collection<?>) {
            Collection<?> immutableList = (Collection<?>) filter.apply(list);
            return immutableList;
        } else if (list instanceof Map<?, ?>) {
            Map<?, ?> immutableList = (Map<?, ?>) filter.apply(list);
            return immutableList;
        }
        return null;
    }


    public List<?> getList() {
        jobProcessList();
        return this.list;
    }

    public Map<?, ?> getMap() {
        jobProcessMap();
        return this.map;
    }
}
