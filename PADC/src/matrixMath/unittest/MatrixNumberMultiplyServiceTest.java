package matrixMath.unittest;

import matrixMath.MatrixNumberMultiplyService;
import models.Matrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixNumberMultiplyServiceTest {

    @Test
    void multiply() throws InterruptedException {

        var m1 = new Matrix(500, 500, 2.0);

        var result = new MatrixNumberMultiplyService(m1, 5).multiply();

        for (var row: result.getRows()) {

            for (var item: row.getData()) {

                assertEquals(item, 10.0);
            }
        }
    }
}