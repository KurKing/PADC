package matrixMath.common;

import models.Matrix;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MatrixService {

    protected final Matrix lhs;
    protected final int lhsRowsAmount;

    public MatrixService(Matrix lhs) {

        this.lhs = lhs;
        this.lhsRowsAmount = lhs.getRowsAmount();
    }

    protected void iterateThroughRows(RowIterationOperator iteration) throws InterruptedException {

        ExecutorService pool = Executors.newCachedThreadPool();
        final int chunkSize = countChunkSize(lhsRowsAmount, 16-1);

        for (int chunk = 0; chunk < lhsRowsAmount; chunk += chunkSize) {

            final int chunkFinal = chunk;

            pool.submit(new Thread(new Runnable() {
                @Override
                public void run() {

                    for (int row = chunkFinal; row < Math.min(chunkFinal + chunkSize, lhsRowsAmount); row++) {

                        iteration.process(row);
                    }
                }
            }));
        }

        pool.shutdown();
        pool.awaitTermination(100L, TimeUnit.SECONDS);
    }

    private int countChunkSize(int lhsRowsAmount, int threadsAmount) {

        int chunkSize = MatrixService.this.lhsRowsAmount / threadsAmount;

        if (chunkSize < 1) { return 1; }

        return chunkSize;
    }
}
