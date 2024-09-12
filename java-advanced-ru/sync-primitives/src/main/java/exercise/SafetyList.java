package exercise;

import java.util.Arrays;

class SafetyList {
    // BEGIN
    private int[] array;
    private int size;

    public SafetyList() {
        array = new int[10];
        size = 0;
    }


    public synchronized void add(int element) {
        if (size == array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }
        array[size] = element;
        size++;
    }


    public synchronized int get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы массива");
        }
        return array[index];
    }


    public synchronized int getSize() {
        return size;
    }
}
    // END

