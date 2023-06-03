package sources.generator;

import models.Matrix;
import models.Row;
import sources.reader.SourcePath;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FilesGenerator {

    public static void main(String[] args) {

        int matrixSize = 400;

        FilesGenerator.writeRandomMatrixInFile(SourcePath.getMBPath(), matrixSize, matrixSize);
        FilesGenerator.writeRandomMatrixInFile(SourcePath.getMTPath(), matrixSize, matrixSize);
        FilesGenerator.writeRandomMatrixInFile(SourcePath.getMZPath(), matrixSize, matrixSize);
        FilesGenerator.writeRandomMatrixInFile(SourcePath.getMEPath(), matrixSize, matrixSize);

        FilesGenerator.writeRandomMatrixInFile(SourcePath.getAPath(), matrixSize, 1);
        FilesGenerator.writeRandomMatrixInFile(SourcePath.getCPath(), matrixSize, 1);
    }

    public static void writeRandomMatrixInFile(String filePath, int row, int column) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            for (int i = 0; i < row; i++) {

                writer.write(Row.instantiateRandomNumberRow(0, column).toString());
                writer.newLine();
            }
            System.out.println(filePath + " written successfully.");

        } catch (IOException e) {
            System.out.println("An error occurred while writing the matrix to file: " + e.getMessage());
        }
    }

    public static void writeMatrixInFile(String filePath, Matrix matrix) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            for (int i = 0; i < matrix.getRowsAmount(); i++) {

                writer.write(matrix.getRow(i).toString());
                writer.newLine();
            }
            System.out.println(filePath + " written successfully.");

        } catch (IOException e) {
            System.out.println("An error occurred while writing the matrix to file: " + e.getMessage());
        }
    }
}
