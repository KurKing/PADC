package matrixMath.services;

import matrixMath.common.MatrixService;
import matrixMath.common.RowIterationOperator;
import models.Matrix;
import models.Row;

public class MatrixAddService extends MatrixService {

    private final Matrix rhs;

    private final Matrix result;

    public MatrixAddService(Matrix lhs, Matrix rhs) {

        super(lhs);

        this.rhs = rhs;
        this.result = new Matrix(lhs.getRowsAmount(), lhs.getColumnsAmount());
    }

    public Matrix add() throws InterruptedException {

        iterateThroughRows(new RowIterationOperator() {
            @Override
            public void process(int row) {

                Row lhsRow = lhs.getRow(row);
                Row rhsRow = rhs.getRow(row);
                Row resultRow = result.getRow(row);

                for (int column = 0; column < lhsRow.size(); column++) {

                    resultRow.addValue(lhsRow.getItem(column) + rhsRow.getItem(column), column);
                }
            }
        });

        return result;
    }
}
