package matrixMath;

import matrixMath.common.MatrixService;
import matrixMath.common.RowIteration;
import models.Matrix;

import java.util.ArrayList;
import java.util.List;

public class MatrixMaxValueService extends MatrixService {

    private List<Double> potentialMaxValues;

    public MatrixMaxValueService(Matrix lhs) {

        super(lhs);

        this.potentialMaxValues = new ArrayList<>(lhs.getRowsAmount());
    }

    public Double findMax() throws InterruptedException {

        iterateThroughRows(new RowIteration() {
            @Override
            public void iterate(int row) {

                potentialMaxValues.add(lhs.getRow(row).getData()
                        .stream()
                        .mapToDouble(Double::doubleValue)
                        .max()
                        .getAsDouble());
            }
        });

        return potentialMaxValues.stream()
                .mapToDouble(Double::doubleValue)
                .max()
                .getAsDouble();
    }
}
