package matrixMath.services;

import matrixMath.common.RowIteration;
import models.Matrix;

public class MatrixMinValueService extends MatrixMaxValueService {

    public MatrixMinValueService(Matrix lhs) {
        super(lhs);
    }

    public Double find() throws InterruptedException {

        iterateThroughRows(new RowIteration() {
            @Override
            public void iterate(int row) {

                potentialResultValues.add(lhs.getRow(row).getData()
                        .stream()
                        .mapToDouble(Double::doubleValue)
                        .min()
                        .getAsDouble());
            }
        });

        return potentialResultValues.stream()
                .mapToDouble(Double::doubleValue)
                .min()
                .getAsDouble();
    }
}
