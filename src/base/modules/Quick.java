package base.modules;

public class Quick extends Algorithm implements Runnable {

    public Quick() {
        super("Quick Sort");
        super.FrameRecord.add(super.Basis.clone());
    }

    public Quick(Number size, Number bound) {
        super(size.intValue(), bound.intValue(), "Quick Sort");
        super.FrameRecord.add(super.Basis.clone());
    }

    @Override
    public void runSimulation() {
        quickSort(0, super.Basis.length - 1);
    }

    private void quickSort(int min, int max) {
        if (min < max) {
            // create partitions
            int partitionIndex = partition(min, max);
            // sort the left partition (lower values)
            quickSort(min, partitionIndex - 1);
            // sort the right partition (higher values)
            quickSort(partitionIndex + 1, max);
        }
    }

    private int partition(int min, int max) {
        int partitionElement;
        int left, right;
        int middle = (min + max) / 2;
        // use the middle data value as the partition element
        partitionElement = super.Basis[middle];
        // move it out of the way for now
        super.swap(super.Basis, middle, min);
        super.FrameRecord.add(super.Basis.clone());
        left = min;
        right = max;
        while (left < right) {
            // search for an element that is > the partition element
            while (left < right && super.Basis[left].compareTo(partitionElement) <= 0)
                left++;
            // search for an element that is < the partition element
            while (super.Basis[right].compareTo(partitionElement) > 0)
                right--;
            // swap the elements
            if (left < right) {
                super.swap(super.Basis, left, right);
                super.FrameRecord.add(super.Basis.clone());
            }
        }
        // move the partition element into place
        super.swap(super.Basis, min, right);
        super.FrameRecord.add(super.Basis.clone());
        return right;
    }

}
