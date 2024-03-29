package base.modules;

import java.util.ArrayList;
import java.util.Random;

public abstract class Algorithm {
    public static final int DEFAULT_SIZE = 10;
    public static final int MINIMUM_SIZE = 5;
    public static final int DEFAULT_BOUND = 100;
    public static final int MINIMUM_BOUND = 10;

    public Integer[] Basis;
    public ArrayList<Integer[]> FrameRecord = new ArrayList<>();
    public String Name;

    public Algorithm(Integer[] input, String name) {
        if (input.length >= MINIMUM_SIZE) {
            this.Basis = input;
        } else {
            throw new IllegalArgumentException("Invalid input size, >= " + MINIMUM_BOUND + " is required");
        }

        this.Name = name;
    }

    public Algorithm(int sampleSize, int bound, String name) {
        System.out.println("Got Size: " + sampleSize + " Got Bound: " + bound);
        if (sampleSize >= MINIMUM_SIZE &&
                bound >= MINIMUM_BOUND) {
            this.Basis = getSample(sampleSize, bound);
        } else {
            this.Basis = getSample(DEFAULT_SIZE, DEFAULT_BOUND);
        }

        this.Name = name;
    }

    public Algorithm(String name) {
        this(DEFAULT_SIZE, DEFAULT_BOUND, name);
    }

    private Integer[] getSample(int sampleSize, int bound) {
        Random gen = new Random();
        ArrayList<Integer> temp = new ArrayList<>(sampleSize);

        for (int index = 0; index < sampleSize; index++) {
            int check = gen.nextInt(bound);
            if (check == 0) {
                check++;
            }
            temp.add(check);
        }
        return temp.toArray(new Integer[sampleSize]);
    }

    public void swap(Integer[] data, int start, int finish) {
        int temp = data[start];
        data[start] = data[finish];
        data[finish] = temp;
    }

    @Override
    public String toString() {
        return this.Name;
    }

}
