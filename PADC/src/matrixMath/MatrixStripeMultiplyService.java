package matrixMath;

import matrixMath.common.MatrixService;
import matrixMath.common.RowIteration;
import models.Matrix;
import models.Row;

public class MatrixStripeMultiplyService extends MatrixService {

    private final Matrix rhs;
    private final Matrix result;
    private final int resultColumns;

    public MatrixStripeMultiplyService(Matrix lhs, Matrix rhs) {

        super(lhs);

        this.rhs = rhs;

        int resultColumns = rhs.getColumnsAmount();

        this.resultColumns = resultColumns;
        this.result = new Matrix(lhs.getRowsAmount(), resultColumns);
    }

    public Matrix multiply() throws InterruptedException {

        setupKahanAdders();
        executeKahan();

        return result;
    }

    private void setupKahanAdders() throws InterruptedException {

        iterateThroughRows(new RowIteration() {
            @Override
            public void iterate(int row) {

                Row resultRow = result.getRow(row);
                Row lhsRow = lhs.getRow(row);

                for (int secondRowIndex = 0; secondRowIndex < rhs.getRowsAmount(); secondRowIndex++) {

                    Row secondDataRow = rhs.getRow(secondRowIndex);

                    for (int column = 0; column < resultColumns; column++) {

                        double valueToAdd = lhsRow.getItem(secondRowIndex) * secondDataRow.getItem(column);
                        resultRow.addKahan(valueToAdd, column);
                    }
                }
            }
        });
    }

    private void executeKahan() throws InterruptedException {

        iterateThroughRows(new RowIteration() {
            @Override
            public void iterate(int row) {

                result.getRow(row).completeKahan();
            }
        });
    }
}
