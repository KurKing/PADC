package matrixMath.unittest;

import matrixMath.services.MatrixMinValueService;
import models.Matrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixMinValueServiceTest {

    @Test
    void findMin() throws InterruptedException {

        var m1 = new Matrix(500, 500, 120);
        m1.getRow(10).setValue(100, 20);

        var result = new MatrixMinValueService(m1).find();

        assertEquals(result, 100);
    }
}