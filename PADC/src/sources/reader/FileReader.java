package sources.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    public static Double readNumberFromFile(String filePath) throws FileNotFoundException {

        return readMatrixFromFile(filePath).get(0).get(0);
    }

    public static List<List<Double>> readMatrixFromFile(String filenPath) throws FileNotFoundException {

        File file = new File(filenPath);

        Scanner scanner = new Scanner(file);

        List<List<Double>> result = new ArrayList<>(50);

        while (scanner.hasNextLine()) {

            String line = scanner.nextLine();

            result.add(convertStringArrayToList(line.split("\\s+")));
        }

        return result;
    }

    private static List<Double> convertStringArrayToList(String[] stringArray) {
        List<Double> doubleList = new ArrayList<>();

        for (String str : stringArray) {

            double value = Double.parseDouble(str);
            doubleList.add(value);
        }

        return doubleList;
    }
}
