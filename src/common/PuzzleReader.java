package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public interface PuzzleReader {

    /**
     * @param path the path where the file is located
     * @return a List with the puzzle
     */
    static List<String> readPuzzle(String path) {

        File input = new File(path);

        if (input.exists()) {

            try {
                BufferedReader reader = new BufferedReader(new FileReader(input));

                String read = reader.readLine();
                List<String> list = new LinkedList<>();

                while (read != null) {

                    list.add(read);
                    read = reader.readLine();
                }

                list.removeIf(Objects::isNull);
                return list;

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else System.out.println("File not found");

        return null;
    }

}
