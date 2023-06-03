package models;

import java.util.ArrayList;
import java.util.List;

public class KahanAdder {

    private List<Double> queue = new ArrayList<>(32);

    synchronized public void put(Double item) {

        queue.add(item);
    }

    public Double getSum() {

        return kahanSum(queue);
    }

    private double kahanSum(List<Double> fa) {

        double sum = 0.0;
        double c = 0.0;

        for (double f : fa) {

            double y = f - c;
            double t = sum + y;

            c = (t - sum) - y;

            sum = t;
        }

        return sum;
    }
}
