package matrixMath.unittest;

import matrixMath.services.MatrixAddVectorService;
import models.Matrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixAddVectorServiceTest {

    @Test
    void add() throws InterruptedException {

        var m1 = new Matrix(500, 500, 1.0);
        var m2 = new Matrix(1, 500, 3.0);

        var result = new MatrixAddVectorService(m1, m2).add();

        for (var row: result.getRows()) {

            for (var item: row.getData()) {

                assertEquals(item, 4);
            }
        }
    }
}