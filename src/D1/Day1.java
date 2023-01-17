package D1;

import java.util.*;

import static common.PuzzleReader.readPuzzle;

public class Day1 {

    public static void main(String[] args) {

        List<String> data = readPuzzle("src/D1/input.txt");
        assert data != null;
        solution(data);
    }

    private static void solution(List<String> data) {

        ArrayList<Integer> caloriesElf = new ArrayList<>();
        int auxSum = 0;

        for (String s : data) {

            if (!s.equals("")) {
                auxSum += Integer.parseInt(s);
            } else {
                caloriesElf.add(auxSum);
                auxSum = 0;
            }

        }
        caloriesElf.add(auxSum);

        Collections.sort(caloriesElf);
        List<Integer> maxCalories = caloriesElf.subList(caloriesElf.size() - 3, caloriesElf.size());

        int totalCalories = 0;
        for (Integer s : maxCalories) {
            totalCalories += s;
            System.out.println(s);
        }

        System.out.println("the sum of the three elves carrying the most calories is: " + totalCalories);
    }
}
