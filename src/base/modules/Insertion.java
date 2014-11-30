package base.modules;

import java.util.Queue;

public class Insertion extends Algorithm implements Runnable {
    public Queue<Integer[]> FrameRecord;

    public Insertion()
    {
        super();
    }

    public Insertion(int size, int bound){
        super(size, bound);
    }

    @Override
    public void runSimulation() {
        for (int index = 1; index < super.Basis.length; index++) {
            int key = super.Basis[index];
            int position = index;
            // shift larger values to the right
            while (position > 0 && super.Basis[position - 1].compareTo(key) > 0) {
                super.Basis[position] = super.Basis[position - 1];
                position--;
            }
            super.Basis[position] = key;

        }
    }
}
