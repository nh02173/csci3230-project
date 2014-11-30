package base.modules;

import java.util.Queue;

public class Selection extends Algorithm implements Runnable {
    public Queue<Integer[]> FrameRecord;

    public Selection() {
        super();
    }

    public Selection(int size, int bound) {
        super(size, bound);
    }

    @Override
    public void runSimulation() {

    }

    public static <T extends Comparable<T>>
    void selectionSort(T[] data) {
        int min;
        T temp;
        for (int index = 0; index < data.length - 1; index++) {
            min = index;
            for (int scan = index + 1; scan < data.length; scan++)
                if (data[scan].compareTo(data[min]) < 0)
                    min = scan;
            swap(data, min, index);
        }
    }
}
