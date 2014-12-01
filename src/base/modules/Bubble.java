package base.modules;

public class Bubble extends Algorithm implements Runnable {

    public Bubble() {
        super("Bubble Sort");
        super.FrameRecord.add(super.Basis.clone());
    }

    public Bubble(Number size, Number bound) {
        super(size.intValue(), bound.intValue(), "Bubble Sort");
        super.FrameRecord.add(super.Basis.clone());
    }

    @Override
    public void runSimulation() {
        int position, scan;

        for (position = super.Basis.length - 1; position >= 0; position--) {
            for (scan = 0; scan <= position - 1; scan++) {
                if (super.Basis[scan].compareTo(super.Basis[scan + 1]) > 0) {
                    super.swap(super.Basis, scan, scan + 1);
                    super.FrameRecord.add(super.Basis.clone());
                }
            }
        }
    }
}
