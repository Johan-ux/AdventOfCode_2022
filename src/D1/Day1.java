package D1;

import java.io.*;
import java.util.*;

public class Day1 {

    public static void main(String[] args) {

        List<String> data = readInput();
        assert data != null;

        solution(data);
    }

    static List<String> readInput() {

        File input = new File("src/D1/input.txt");

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

    static void solution(List<String> data) {

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

        /* part 1
        for (Integer integer : caloriesElf) {
            maxCalories = Math.max(maxCalories, integer);
        }
        System.out.println("max calories: " + maxCalories);
         */

        //part2
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
