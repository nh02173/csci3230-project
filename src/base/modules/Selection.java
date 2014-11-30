package base.modules;

import java.util.Queue;

public class Selection extends Algorithm implements Runnable {
    public Queue<Integer[]> FrameRecord;

    public Selection() {
        super();
        FrameRecord.add(super.Basis.clone());
    }

    public Selection(int size, int bound) {
        super(size, bound);
        FrameRecord.add(super.Basis.clone());
    }

    @Override
    public void runSimulation() {
        int min;

        for (int index = 0; index < super.Basis.length - 1; index++) {
            min = index;

            for (int scan = index + 1; scan < super.Basis.length; scan++) {
                if (super.Basis[scan].compareTo(super.Basis[min]) < 0) {
                    min = scan;
                }
            }

            super.swap(super.Basis, min, index);
            FrameRecord.add(super.Basis.clone());
        }
    }
}
