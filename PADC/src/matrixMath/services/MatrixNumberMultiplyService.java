package matrixMath.services;

import matrixMath.common.MatrixService;
import matrixMath.common.RowIterationOperator;
import models.Matrix;
import models.Row;

public class MatrixNumberMultiplyService extends MatrixService {

    private final double rhs;

    public MatrixNumberMultiplyService(Matrix lhs, double rhs) {

        super(lhs);

        this.rhs = rhs;
    }

    public Matrix multiply() throws InterruptedException {

        iterateThroughRows(new RowIterationOperator() {
            @Override
            public void process(int row) {

                Row lhsRow = lhs.getRow(row);

                for (int column = 0; column < lhsRow.size(); column++) {

                    double item = lhsRow.getItem(column) * rhs;
                    lhsRow.setValue(item, column);
                }
            }
        });

        return lhs;
    }
}
