package base.modules;

import java.util.Queue;

public class Quick extends Algorithm implements Runnable {
    public Queue<Integer[]> FrameRecord;

    public Quick() {
        super();
    }

    public Quick(int size, int bound) {
        super(size, bound);
    }

    @Override
    public void runSimulation() {

    }

    public static <T extends Comparable<T>>
    void quickSort(T[] data) {
        quickSort(data, 0, data.length - 1);
    }

    private static <T extends Comparable<T>>
    void quickSort(T[] data, int min, int max) {
        if (min < max) {
            // create partitions
            int indexofpartition = partition(data, min, max);
            // sort the left partition (lower values)
            quickSort(data, min, indexofpartition - 1);
            // sort the right partition (higher values)
            quickSort(data, indexofpartition + 1, max);
        }

    }

    private static <T extends Comparable<T>>
    int partition(T[] data, int min, int max) {
        T partitionelement;
        int left, right;
        int middle = (min + max) / 2;
        // use the middle data value as the partition element
        partitionelement = data[middle];
        // move it out of the way for now


        swap(data, middle, min);
        left = min;
        right = max;
        while (left < right) {
            // search for an element that is > the partition element
            while (left < right && data[left].compareTo(partitionelement) <= 0)
                left++;
            // search for an element that is < the partition element
            while (data[right].compareTo(partitionelement) > 0)
                right--;
            // swap the elements
            if (left < right)
                swap(data, left, right);
        }
        // move the partition element into place
        swap(data, min, right);
        return right;
    }

}
