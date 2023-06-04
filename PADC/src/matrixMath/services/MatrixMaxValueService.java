package matrixMath.services;

import matrixMath.common.MatrixService;
import matrixMath.common.RowIterationOperator;
import models.Matrix;

import java.util.ArrayList;
import java.util.List;

public class MatrixMaxValueService extends MatrixService {

    protected List<Double> potentialResultValues;

    public MatrixMaxValueService(Matrix lhs) {

        super(lhs);

        this.potentialResultValues = new ArrayList<>(lhs.getRowsAmount());
    }

    public Double find() throws InterruptedException {

        iterateThroughRows(new RowIterationOperator() {
            @Override
            public void process(int row) {

                potentialResultValues.add(lhs.getRow(row).getData()
                        .stream()
                        .mapToDouble(Double::doubleValue)
                        .max()
                        .getAsDouble());
            }
        });

        return potentialResultValues.stream()
                .mapToDouble(Double::doubleValue)
                .max()
                .getAsDouble();
    }
}
