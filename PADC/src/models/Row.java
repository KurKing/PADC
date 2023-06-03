package models;

import java.util.ArrayList;
import java.util.List;

public class Row {

    private int index;
    private int size;

    private List<Double> data;
    private List<KahanAdder> kahanAdders;

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

    public double getItem(int index) {
        return data.get(index);
    }

    public List<Double> getData() {
        return data;
    }

    synchronized public void setValue(double value, int index) {

        data.set(index, value);
    }

    synchronized public void addValue(double value, int index) {

        data.set(index, data.get(index) + value);
    }

    synchronized public void addKahan(double value, int index) {

        kahanAdders.get(index).put(value);
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
}
