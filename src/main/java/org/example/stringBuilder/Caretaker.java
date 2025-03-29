package org.example.stringBuilder;

import java.util.Deque;
import java.util.LinkedList;

public class Caretaker {
    private final Deque<CustomStringBuilder.Memento> mementos = new LinkedList<>();

    public void setMemento(CustomStringBuilder.Memento memento) {
        mementos.addLast(memento);
    }

    public CustomStringBuilder.Memento getMemento() {
        return mementos.pollLast();
    }
}
