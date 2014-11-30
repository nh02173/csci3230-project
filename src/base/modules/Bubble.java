package base.modules;

import java.util.Queue;

public class Bubble extends Algorithm implements Runnable {
    public Queue<Integer[]> FrameRecord;

    public Bubble()
    {
        super();
    }

    public Bubble(int size, int bound){
        super(size, bound);
    }

    @Override
    public void runSimulation() {
        FrameRecord.add(super.Basis.clone());
        int position, scan;

        for (position = super.Basis.length - 1; position >= 0; position--)
        {
            for (scan = 0; scan <= position - 1; scan++)
            {
                if (super.Basis[scan].compareTo(super.Basis[scan+1]) > 0) {
                    super.swap(scan, scan + 1);
                    FrameRecord.add(super.Basis.clone());
                }
            }
        }
    }
}
