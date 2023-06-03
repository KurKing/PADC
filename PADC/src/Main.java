import matrixMath.services.MatrixAddService;
import matrixMath.services.MatrixMaxValueService;
import matrixMath.services.MatrixNumberMultiplyService;
import matrixMath.services.MatrixStripeMultiplyService;
import models.Matrix;
import sources.generator.FilesGenerator;
import sources.reader.FileReader;
import sources.reader.SourcePath;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.Instant;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        MG();
//        X();
    }

    private static void MG() throws FileNotFoundException, InterruptedException {

        var A = new Matrix(SourcePath.getAPath());
        var C = new Matrix(SourcePath.getCPath());

        var MB = new Matrix(SourcePath.getMBPath());
        var MT = new Matrix(SourcePath.getMTPath());
        var MZ = new Matrix(SourcePath.getMZPath());
        var ME = new Matrix(SourcePath.getMEPath());

        var smallA = FileReader.readNumberFromFile(SourcePath.getSmallAPath());

        Instant start = Instant.now();

        var maxOfAPlusC = (new MatrixMaxValueService((new MatrixAddService(
                A,
                C
        )).add())).find();

        var firstAdder = (new MatrixStripeMultiplyService(
                new MatrixNumberMultiplyService(MB, maxOfAPlusC).multiply(),
                MT
        )).multiply();

        var secondAdder = (new MatrixNumberMultiplyService(
                (new MatrixStripeMultiplyService(MZ, ME)).multiply(),
                -smallA
        )).multiply();

        var MG = (new MatrixAddService(firstAdder, secondAdder)).add();

        System.out.println("Final time for MG count: " + Duration.between(start, Instant.now()).toMillis());

        FilesGenerator.writeMatrixInFile(SourcePath.getMGPath(), MG);
    }

    private static void X() throws FileNotFoundException {

        FilesGenerator.writeMatrixInFile(SourcePath.getXPath(), new Matrix(3, 3, 1));
    }
}