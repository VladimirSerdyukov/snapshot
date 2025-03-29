package org.example;

import org.example.stringBuilder.CustomStringBuilder;

public class Main {
    public static void main(String[] args) {
        CustomStringBuilder string = new CustomStringBuilder();

        string.append("one step");
        string.append("!");
        string.append("!!!");
        System.out.println(string);

        string.undo();
        System.out.println(string);
        string.undo();
        System.out.println(string);
        string.undo();
        System.out.println(string);
        string.undo();
        string.undo();
        string.undo();
        string.undo();
        string.undo();
        string.undo();
        string.undo();
    }
}