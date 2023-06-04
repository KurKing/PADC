package matrixMath.common;

import models.Matrix;

import java.util.ArrayList;
import java.util.List;

public class MatrixService {

    protected final Matrix lhs;
    protected final int lhsRowsAmount;

    public MatrixService(Matrix lhs) {

        this.lhs = lhs;
        this.lhsRowsAmount = lhs.getRowsAmount();
    }

    protected void iterateThroughRows(RowIterationOperator iteration) throws InterruptedException {

        int threadsAmount = 16-1;
        List<Thread> threads = new ArrayList<>(threadsAmount);

        final int chunkSize = countChunkSize(lhsRowsAmount, threadsAmount);

        for (int chunk = 0; chunk < lhsRowsAmount; chunk += chunkSize) {

            final int chunkFinal = chunk;
            Thread task = new Thread(new Runnable() {
                @Override
                public void run() {

                    for (int row = chunkFinal; row < Math.min(chunkFinal + chunkSize, lhsRowsAmount); row++) {

                        iteration.process(row);
                    }
                }
            });

            threads.add(task);
            task.start();
        }

        for (Thread thread: threads) {

            thread.join();
        }
    }

    private int countChunkSize(int lhsRowsAmount, int threadsAmount) {

        int chunkSize = MatrixService.this.lhsRowsAmount / threadsAmount;

        if (chunkSize < 1) { return 1; }

        return chunkSize;
    }
}
