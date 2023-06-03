import matrixMath.services.*;
import models.Matrix;
import models.Row;
import sources.generator.FilesGenerator;
import sources.reader.FileReader;
import sources.reader.SourcePath;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.Instant;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        var mgTime = MG();

        System.out.println("#######################################################################\n");

        var xTime = X();

        System.out.println("Final time for MG count: " + mgTime);
        System.out.println("Final time for X count: " + xTime);
    }

    private static long MG() throws FileNotFoundException, InterruptedException {

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
        var mgTime = Duration.between(start, Instant.now()).toMillis();

        FilesGenerator.writeMatrixInFile(SourcePath.getMGPath(), MG);
        System.out.println("");
        MG.print();

        return mgTime;
    }

    private static long X() throws FileNotFoundException, InterruptedException {

        var A = new Matrix(new Row(0, new Matrix(SourcePath.getAPath()).getVector()));
        var C = new Matrix(SourcePath.getCPath());

        var MB = new Matrix(SourcePath.getMBPath());

        Instant start = Instant.now();

        var minusMinC = -(new MatrixMinValueService(C)).find();
        var X = new MatrixAddVectorService(
                (new MatrixNumberMultiplyService(C, minusMinC)).multiply(),
                (new MatrixStripeMultiplyService(A, MB)).multiply()
        ).add();
        var xTime = Duration.between(start, Instant.now()).toMillis();

        FilesGenerator.writeMatrixInFile(SourcePath.getXPath(), X);
        System.out.println("");
        X.print();

        return xTime;
    }
}