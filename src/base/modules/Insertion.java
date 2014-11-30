package base.modules;

public class Insertion extends Algorithm implements Runnable {

    public Insertion()
    {
        super("Insertion Sort");
        super.FrameRecord.add(super.Basis.clone());
    }

    public Insertion(Number size, Number bound){
        super(size.intValue(), bound.intValue(), "Insertion Sort");
        super.FrameRecord.add(super.Basis.clone());
    }

    @Override
    public void runSimulation() {
        for (int index = 1; index < super.Basis.length; index++) {
            int key = super.Basis[index];
            int position = index;
            // shift larger values to the right
            while (position > 0 && super.Basis[position - 1].compareTo(key) > 0) {
                super.Basis[position] = super.Basis[position - 1];
                super.FrameRecord.add(super.Basis.clone());
                position--;
            }
            super.Basis[position] = key;
        }
    }
}
