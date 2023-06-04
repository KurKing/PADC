package matrixMath.services;

import matrixMath.common.RowIterationOperator;
import models.Matrix;

public class MatrixMinValueService extends MatrixMaxValueService {

    public MatrixMinValueService(Matrix lhs) {
        super(lhs);
    }

    public Double find() throws InterruptedException {

        iterateThroughRows(new RowIterationOperator() {
            @Override
            public void process(int row) {

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
