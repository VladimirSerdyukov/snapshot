package org.example.stringBuilder;

import java.io.Serial;
import java.util.Arrays;
import java.util.stream.IntStream;

public class CustomStringBuilder extends AbstractCustomStringBuilder
        implements java.io.Serializable, Comparable<CustomStringBuilder>,
        CharSequence {

    @Serial
    private static final long serialVersionUID = 1L;

    private Caretaker box = new Caretaker();

    public CustomStringBuilder() {
        createMemento();
    }

    public CustomStringBuilder(int[] code) {
        char[] chars = new char[code.length];
        for (int i = 0; i < code.length; i++) {
            chars[i] = Character.toChars(code[i])[0];
        }
        super(chars);
        createMemento();
    }

    public CustomStringBuilder(char[] chars) {
        super(chars);
        createMemento();
    }

    public CustomStringBuilder(String str) {
        super(str);
        createMemento();
    }

    @Override
    public void append(String string){
        super.append(string);
        createMemento();
    }

    @Override
    public int length() {
        return value.length;
    }

    @Override
    public char charAt(int index) {
        return value[index];
    }

    @Override
    public boolean isEmpty() {
        return CharSequence.super.isEmpty();
    }

    @Override
    public CustomStringBuilder subSequence(int start, int end) {
        return new CustomStringBuilder(Arrays.copyOfRange(value, start, end));
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public IntStream chars() {
        return IntStream.range(0, value.length).map(i -> value[i]);
    }

    @Override
    public IntStream codePoints() {
        return IntStream.range(0, value.length).map(i -> value[i]);
    }

    @Override
    public int compareTo(CustomStringBuilder o) {
        return super.compareTo(o);
    }

    private char[] getState() {
        return value;
    }

    private void setState(char[] state) {
        this.value = state;
    }

    private void createMemento() {
        box.setMemento(new Memento(getState()));
    }

    public void undo() {
        char[] boxValue;
        try {
            boxValue = box.getMemento().getState();
            this.value = boxValue;
        } catch (NullPointerException e) {
            System.out.println("Вы отменили последнее действие");
        }

    }

    public class Memento {
        private final char[] state;

        private Memento(char[] state) {
            this.state = state;
        }

        private char[] getState(){
            return this.state;
        }
    }

}


