package matrixMath.unittest;

import matrixMath.services.MatrixMaxValueService;
import models.Matrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixMaxValueServiceTest {

    @Test
    void findMax() throws InterruptedException {

        var m1 = new Matrix(500, 500, 1.0);
        m1.getRow(10).setValue(100, 20);

        var result = new MatrixMaxValueService(m1).find();

        assertEquals(result, 100);
    }
}