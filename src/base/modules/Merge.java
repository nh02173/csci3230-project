package base.modules;

public class Merge extends Algorithm implements Runnable {

    public Merge() {
        super("Merge Sort");
        super.FrameRecord.add(super.Basis.clone());
    }

    public Merge(Number size, Number bound) {
        super(size.intValue(), bound.intValue(), "Merge Sort");
        super.FrameRecord.add(super.Basis.clone());
    }

    @Override
    public void runSimulation() {
        mergeSort(0, super.Basis.length - 1);
    }

    private void mergeSort(int min, int max) {
        if (min < max) {
            int mid = (min + max) / 2;
            mergeSort(min, mid);
            mergeSort(mid + 1, max);
            merge(min, mid, max);
        }
    }

    private void merge(int first, int mid, int last) {
        Integer[] temp = (Integer[]) (new Comparable[super.Basis.length]);
        int first1 = first, last1 = mid; // endpoints of first subarray
        int first2 = mid + 1, last2 = last; // endpoints of second subarray
        int index = first1; // next index open in temp array
        // Copy smaller item from each subarray into temp until one
        // of the subarrays is exhausted
        while (first1 <= last1 && first2 <= last2) {
            if (super.Basis[first1].compareTo(super.Basis[first2]) < 0) {
                temp[index] = super.Basis[first1];
                first1++;
            } else {
                temp[index] = super.Basis[first2];
                first2++;
            }
            index++;
        }
        // Copy remaining elements from first subarray, if any
        while (first1 <= last1) {
            temp[index] = super.Basis[first1];
            first1++;
            index++;
        }
        // Copy remaining elements from second subarray, if any
        while (first2 <= last2) {
            temp[index] = super.Basis[first2];
            first2++;
            index++;
        }
        // Copy merged data into original array
        for (index = first; index <= last; index++) {
            super.Basis[index] = temp[index];
            super.FrameRecord.add(super.Basis.clone());
        }
    }
}
