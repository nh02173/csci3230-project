package base.modules;

import java.util.ArrayList;
import java.util.Random;

public abstract class Algorithm {
    public static final int DEFAULT_SIZE = 10;
    public static final int MINIMUM_SIZE = 10;
    public static final int DEFAULT_BOUND = 100;
    public static final int MINIMUM_BOUND = 10;

    public Integer[]Basis;

    public Algorithm(Integer[] input) {
        if (input.length >= MINIMUM_SIZE) {
            Basis = input;
        } else {
            throw new IllegalArgumentException("Invalid input size, >= " + MINIMUM_BOUND + " is required");
        }
    }

    public Algorithm(int sampleSize, int bound) {
        if (sampleSize >= MINIMUM_SIZE &&
                bound >= MINIMUM_BOUND) {
            Basis = getSample(sampleSize, bound);
        } else {
            Basis = getSample(DEFAULT_SIZE, DEFAULT_BOUND);
        }
    }

    public Algorithm(){
        this(DEFAULT_SIZE, DEFAULT_BOUND);
    }

    private Integer[] getSample(int sampleSize, int bound) {
        Random gen = new Random();
        ArrayList<Integer> temp = new ArrayList<Integer>(sampleSize);

        for (int index = 0; index < sampleSize; index++) {
            temp.add(gen.nextInt(bound));
        }
        return temp.toArray (new Integer [sampleSize]);
    }

    public void swap(Integer[] data, int start, int finish)
    {
        int temp = data[start];
        data[start] = data[finish];
        data[finish] = temp;
    }
}
