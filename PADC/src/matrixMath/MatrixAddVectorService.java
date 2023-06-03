package matrixMath;

import matrixMath.common.MatrixService;
import matrixMath.common.RowIteration;
import models.Matrix;
import models.Row;

import java.util.List;

public class MatrixAddVectorService extends MatrixService {

    private final List<Double> vector;

    public MatrixAddVectorService(Matrix lhs, Matrix rhs) {

        super(lhs);
        this.vector = rhs.getRow(0).getData();
    }

    public Matrix add() throws InterruptedException {

        iterateThroughRows(new RowIteration() {
            @Override
            public void iterate(int row) {

                Row rowModel = lhs.getRow(row);
                Double vectorItem = vector.get(row);

                for (int i = 0; i < rowModel.size(); i++) {

                    rowModel.addValue(vectorItem, i);
                }
            }
        });

        return lhs;
    }
}
