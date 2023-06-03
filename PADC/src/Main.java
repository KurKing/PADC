import models.Matrix;
import sources.generator.FilesGenerator;
import sources.reader.FileReader;
import sources.reader.SourcePath;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        MG();
        X();

        System.out.println(FileReader.readNumberFromFile(SourcePath.getSmallAPath()));
    }

    private static void MG() {

        FilesGenerator.writeMatrixInFile(SourcePath.getMGPath(), new Matrix(3, 3, 1));
    }

    private static void X() {

        FilesGenerator.writeMatrixInFile(SourcePath.getXPath(), new Matrix(3, 3, 1));
    }
}