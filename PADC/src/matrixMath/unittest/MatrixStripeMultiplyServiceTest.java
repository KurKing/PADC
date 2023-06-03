package matrixMath.unittest;

import matrixMath.services.MatrixStripeMultiplyService;
import models.Matrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixStripeMultiplyServiceTest {

    @Test
    void multiply() throws InterruptedException {

        var m1 = new Matrix(500, 500, 0.1);
        var m2 = new Matrix(500, 500, 0.1);

        var result = new MatrixStripeMultiplyService(m1, m2).multiply();

        for (var row: result.getRows()) {

            for (var item: row.getData()) {

                assertEquals(item, 500);
            }
        }
    }
}