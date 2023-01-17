package D4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static common.PuzzleReader.readPuzzle;

public class Day4 {
    public static void main(String[] args) {
        List<String> data = readPuzzle("src/D4/input.txt");
        assert data != null;

        part1(data);
        System.out.println("-----");
        part2(data);
    }

    private static void part1(List<String> data) {
        int rangeFullyContain = 0;

        for (String s : data) {

            String[] pairs = s.split(",");
            String[] left = pairs[0].split("-");
            String[] right = pairs[1].split("-");

            List<Integer> leftRange = Ranges(Integer.parseInt(left[0]), Integer.parseInt(left[1]));
            List<Integer> rightRange = Ranges(Integer.parseInt(right[0]), Integer.parseInt(right[1]));

            boolean leftContainsRight = new HashSet<>(leftRange).containsAll(rightRange);
            boolean rightContainsLeft = new HashSet<>(rightRange).containsAll(leftRange);

            if (leftContainsRight | rightContainsLeft) {
                rangeFullyContain++;
            }
        }
        System.out.println("Part 1: " + rangeFullyContain);
    }

    private static void part2(List<String> data) {
        int rangeFullyContain = 0;

        for (String s : data) {

            String[] pairs = s.split(",");
            String[] left = pairs[0].split("-");
            String[] right = pairs[1].split("-");

            List<Integer> leftRange = Ranges(Integer.parseInt(left[0]), Integer.parseInt(left[1]));
            List<Integer> rightRange = Ranges(Integer.parseInt(right[0]), Integer.parseInt(right[1]));

            if (leftRange.stream().anyMatch(rightRange::contains) ||
                    rightRange.stream().anyMatch(leftRange::contains)) {
                rangeFullyContain++;
            }

        }
        System.out.println("Part 2: " + rangeFullyContain);
    }

    private static List<Integer> Ranges(int start, int end) {
        List<Integer> range = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            range.add(i);
        }
        return range;
    }
}
