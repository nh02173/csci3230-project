package base.modules;

import java.util.Queue;

public class Merge extends Algorithm implements Runnable {
    public Queue<Integer[]> FrameRecord;

    public Merge() {
        super();
    }

    public Merge(int size, int bound) {
        super(size, bound);
    }

    @Override
    public void runSimulation() {

    }

    public static <T extends Comparable<T>>
    void mergeSort(T[] data)
    {
        mergeSort(data, 0, data.length - 1);
    }

    private static <T extends Comparable<T>>
    void mergeSort(T[] data, int min, int max)
    {
        if (min < max)
        {
            int mid = (min + max) / 2;
            mergeSort(data, min, mid);
            mergeSort(data, mid+1, max);
            merge(data, min, mid, max);
        }
    }

    private static <T extends Comparable<T>>
    void merge(T[] data, int first, int mid, int last) {
        T[] temp = (T[]) (new Comparable[data.length]);
        int first1 = first, last1 = mid; // endpoints of first subarray
        int first2 = mid + 1, last2 = last; // endpoints of second subarray
        int index = first1; // next index open in temp array
        // Copy smaller item from each subarray into temp until one
        // of the subarrays is exhausted
        while (first1 <= last1 && first2 <= last2) {
            if (data[first1].compareTo(data[first2]) < 0) {
                temp[index] = data[first1];
                first1++;
            } else {
                temp[index] = data[first2];
                first2++;
            }


            index++;
        }
        // Copy remaining elements from first subarray, if any
        while (first1 <= last1) {
            temp[index] = data[first1];
            first1++;
            index++;
        }
        // Copy remaining elements from second subarray, if any
        while (first2 <= last2) {
            temp[index] = data[first2];
            first2++;
            index++;
        }
        // Copy merged data into original array
        for (index = first; index <= last; index++)
            data[index] = temp[index];
    }

}
