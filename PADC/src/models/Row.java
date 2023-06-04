package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Row {

    private int index;
    private int size;

    private List<Double> data;
    private List<KahanAdder> kahanAdders;

    private Semaphore semaphore = new Semaphore(1);

    public Row(int index, List<Double> data) {

        this.index = index;
        this.data = data;

        int size = data.size();
        this.size = size;

        this.kahanAdders = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            kahanAdders.add(new KahanAdder());
        }
    }

    public static Row instantiateClearRow(int index, int size) {

       List<Double> row = new ArrayList<>(size);
        for (int i = 0; i < size; i++) { row.add(0.0); }

        return new Row(index, row);
    }

    public static Row instantiateOneNumberRow(int index, int size, double number) {

        List<Double> row = new ArrayList<>(size);
        for (int i = 0; i < size; i++) { row.add(number); }

        return new Row(index, row);
    }

    public static Row instantiateRandomNumberRow(int index, int size) {

        Random random = new Random();
        List<Double> row = new ArrayList<>(size);

        for (int i = 0; i < size; i++) { row.add(random.nextDouble() * 10); }

        return new Row(index, row);
    }

    public double getItem(int index) {
        return data.get(index);
    }

    public List<Double> getData() {
        return data;
    }

    public void setValue(double value, int index) {

        try {

            semaphore.acquire();
            data.set(index, value);
            semaphore.release();
        } catch (InterruptedException e) { throw new RuntimeException(e); }
    }

    public void addValue(double value, int index) {

        try {

            semaphore.acquire();
            data.set(index, data.get(index) + value);
            semaphore.release();
        } catch (InterruptedException e) { throw new RuntimeException(e); }
    }

    public void addKahan(double value, int index) {

        try {

            semaphore.acquire();
            kahanAdders.get(index).put(value);
            semaphore.release();
        } catch (InterruptedException e) { throw new RuntimeException(e); }
    }

    public void completeKahan() {

        for (int i = 0; i < size; i++) {

            data.set(i, kahanAdders.get(i).getSum());
        }
    }

    public void print() {

        for (double item: data) {

            System.out.print(item + " ");
        }
    }

    public int size() {

        return size;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < data.size(); i++) {
            sb.append(data.get(i) + " ");
        }

        return sb.toString();
    }
}
