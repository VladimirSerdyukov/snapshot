package org.example.stringBuilder;

public abstract class AbstractCustomStringBuilder {

    char[] value;

    AbstractCustomStringBuilder() {
        value = new char[0];
    }

    AbstractCustomStringBuilder(int capacity) {
        value = new char[capacity];
    }

    AbstractCustomStringBuilder(String str) {
        value = str.toCharArray();
    }

    AbstractCustomStringBuilder(char[] str) {
        value = str;
    }


    protected char[] getValue() {
        return value;
    }

    public void append(String str) {
        char[] temp = this.value;
        char[] add = str.toCharArray();
        int capacity = add.length;
        int countOld = this.value.length;
        this.value = new char[capacity + countOld];
        System.arraycopy(temp, 0, this.value, 0, countOld);
        System.arraycopy(add, 0, this.value, countOld, capacity);
    }

    public int count() {
        return value.length;
    }


    int compareTo(AbstractCustomStringBuilder another) {
        if (this == another) {
            return 0;
        }

        char[] anotherValue = another.getValue();

        int count = Math.min(value.length, another.count());
        char[] value2 = another.getValue();
        int v1 = 0;
        int v2 = 0;
        for(int i = count - 1; i >= 0; i--){
            v1 = value[i];
            v2 = value2[i];
            if(v1 != v2){
                return v1 - v2;
            }
        }
        if(value.length > count) {
            return 1;
        } else {
            return -1;
        }
    }
}
