package matrixMath.common;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RecursiveAction;

public class MatrixForkJoinAction extends RecursiveAction {

    private final RowIterationOperator iteration;
    private final BlockingQueue<Integer> queue;
    private final int chunkSize;

    private final int start;
    private final int end;

    public MatrixForkJoinAction(RowIterationOperator iteration, BlockingQueue<Integer> queue,
                                int chunkSize, int start, int end) {
        this.iteration = iteration;
        this.queue = queue;
        this.chunkSize = chunkSize;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {

        if (end - start > chunkSize) {

            int divider = (end + start) / 2;

            var action1 = new MatrixForkJoinAction(iteration, queue, chunkSize, start, divider);
            var action2 = new MatrixForkJoinAction(iteration, queue, chunkSize, divider, end);

            action1.fork();
            action2.fork();

            action1.join();
            action2.join();

        } else {

            Integer rowNumber;
            while ((rowNumber = queue.poll()) != null) {
                iteration.process(rowNumber);
            }
        }
    }
}
