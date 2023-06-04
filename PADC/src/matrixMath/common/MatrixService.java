package matrixMath.common;

import models.Matrix;

import java.util.concurrent.*;

public class MatrixService {

    protected final Matrix lhs;
    protected final int lhsRowsAmount;

    public MatrixService(Matrix lhs) {

        this.lhs = lhs;
        this.lhsRowsAmount = lhs.getRowsAmount();
    }

    protected void iterateThroughRows(RowIterationOperator iteration) throws InterruptedException {

        var pool = new ForkJoinPool(32);

        pool.invoke(new MatrixForkJoinAction(
                iteration,
                32,
                0,
                lhsRowsAmount));
    }
}
