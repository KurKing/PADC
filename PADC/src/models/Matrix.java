package models;

import sources.reader.FileReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Matrix {

    private final List<Row> rows = new ArrayList<>();

    public Matrix(String filePath) throws FileNotFoundException {

        var data = FileReader.readMatrixFromFile(filePath);

        for (int i = 0; i < data.size(); i++) {

            rows.add(new Row(i, data.get(i)));
        }
    }

    public Matrix(int rows, int columns) {

        for (int row = 0; row < rows; row++) {

            this.rows.add(Row.instantiateClearRow(row, columns));
        }
    }

    public Matrix(int rows, int columns, double number) {

        for (int i = 0; i < rows; i++) {

            this.rows.add(Row.instantiateOneNumberRow(i, columns, number));
        }
    }

    public Matrix(Row row) {

        this.rows.add(row);
    }

    public double getItem(int row, int column) {

        return rows.get(row).getItem(column);
    }

    public List<Row> getRows() {

        return rows;
    }

    public Row getRow(int row) {

        return rows.get(row);
    }

    public int getRowsAmount() {

        return rows.size();
    }

    public int getColumnsAmount() {

        return rows.get(0).size();
    }

    public List<Double> getVector() {

        List<Double> vector = new ArrayList<>(rows.size());

        for (var row: rows) {

            vector.add(row.getItem(0));
        }

        return vector;
    }

    public void print() {

        for (Row row : rows) {

            row.print();
            System.out.println("");
        }

        System.out.println("");
    }
}
