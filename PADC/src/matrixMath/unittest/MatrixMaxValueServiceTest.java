package matrixMath.unittest;

import matrixMath.MatrixMaxValueService;
import models.Matrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixMaxValueServiceTest {

    @Test
    void findMax() throws InterruptedException {

        var m1 = new Matrix(500, 500, 1.0);

        var result = new MatrixMaxValueService(m1).findMax();

        assertEquals(result, 1);
    }
}