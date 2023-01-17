package D6;

import java.util.List;

import static common.PuzzleReader.readPuzzle;

public class Day6 {

    public static void main(String[] args) {
        List<String> data = readPuzzle("src/D6/input.txt");
        assert data != null;

        part1(data);
        System.out.println("-----");
        part2(data);
    }

    private static void part1(List<String> data) {
        for (int i = 0; i < data.get(0).length(); i++) {

            String marker = data.get(0).substring(i, i + 4);
            boolean coincidence = findCoincidences(marker);

            if (!coincidence) {
                System.out.println(marker + " " + (i + 4));
                break;
            }
            if (i == data.get(0).length() - 4) break;
        }
    }

    private static void part2(List<String> data) {
        for (int i = 0; i < data.get(0).length(); i++) {

            String marker = data.get(0).substring(i, i + 14);

            boolean coincidence = findCoincidences(marker);

            if (!coincidence) {
                System.out.println(marker + " " + (i + 14));
                break;
            }
            if (i == data.get(0).length() - 14) break;
        }
    }

    private static boolean findCoincidences(String word) {
        char[] letters = word.toCharArray();

        for (int i = 0; i < letters.length; i++) {
            for (int j = i + 1; j < letters.length; j++) {
                if (letters[i] == letters[j]) return true;
            }
        }
        return false;
    }
}