package sources.generator;

import models.Matrix;
import models.Row;
import sources.reader.SourcePath;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FilesGenerator {

    public static void main(String[] args) {

        FilesGenerator.writeRandomMatrixInFile(SourcePath.getMBPath(), 500, 500);
        FilesGenerator.writeRandomMatrixInFile(SourcePath.getMTPath(), 500, 500);
        FilesGenerator.writeRandomMatrixInFile(SourcePath.getMZPath(), 500, 500);
        FilesGenerator.writeRandomMatrixInFile(SourcePath.getMEPath(), 500, 500);

        FilesGenerator.writeRandomMatrixInFile(SourcePath.getAPath(), 500, 1);
        FilesGenerator.writeRandomMatrixInFile(SourcePath.getCPath(), 500, 1);
    }

    public static void writeRandomMatrixInFile(String filePath, int row, int column) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            for (int i = 0; i < row; i++) {

                writer.write(Row.instantiateRandomNumberRow(0, column).toString());
                writer.newLine();
            }
            System.out.println(filePath + " written to file successfully.");

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
            System.out.println(filePath + " written to file successfully.");

        } catch (IOException e) {
            System.out.println("An error occurred while writing the matrix to file: " + e.getMessage());
        }
    }
}
