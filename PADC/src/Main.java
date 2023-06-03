import matrixMath.MatrixAddService;
import matrixMath.MatrixAddVectorService;
import matrixMath.MatrixNumberMultiplyService;
import matrixMath.MatrixStripeMultiplyService;
import models.Matrix;
import sources.reader.SourcePath;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        String[] files = new String[] {
                SourcePath.getMBPath(),
                SourcePath.getMZPath()
        };

        var m1 = new Matrix(500, 500, 1.0);
        var m2 = new Matrix(1, 500, 3.0);

//        var result = (new MatrixStripeMultiplyService(m1, m2)).multiply();
//        var result = (new MatrixAddService(m1, m2)).add();
//        var result = (new MatrixNumberMultiplyService(m1, -2.0)).multiply();
//        var result = (new MatrixAddVectorService(m1, m2)).add();

        m2 = (new MatrixNumberMultiplyService(m2, -1)).multiply();
        var result = (new MatrixAddVectorService(m1, m2)).add();

        result.print();
    }
}