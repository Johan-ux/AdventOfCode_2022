package D3;

import java.util.HashMap;
import java.util.List;

import static common.PuzzleReader.readPuzzle;

public class Day3 {

    public static void main(String[] args) {
        List<String> data = readPuzzle("src/D3/input.txt");
        assert data != null;

        part1(data);
        part2(data);
    }

    public static void part1(List<String> data) {
        HashMap<Character, Integer> priorityOrder = new HashMap<>();

        for (int i = 0; i < 26; i++) {
            priorityOrder.put((char) ('a' + i), i + 1);
            priorityOrder.put((char) ('A' + i), i + 27);
        }
        int totalPriorities = 0;

        for (String s : data) {
            String bag1 = s.substring(0, (s.length() / 2));
            String bag2 = s.substring((s.length() / 2));

            char priority = 0;
            for (int i = 0; i < bag2.length(); i++) {
                for (int j = 0; j < bag1.length(); j++) {
                    priority = bag1.charAt(j);
                    if (priority == bag2.charAt(i)) break;
                }
                if (bag2.charAt(i) == priority) {
                    totalPriorities += priorityOrder.get(priority);
                    break;
                }
            }
        }

        System.out.println("Part 1: " + totalPriorities);
    }

    public static void part2(List<String> data) {

        HashMap<Character, Integer> priorityOrder = new HashMap<>();
        int totalPriorities = 0;

        for (int i = 0; i < 26; i++) {
            priorityOrder.put((char) ('a' + i), i + 1);
            priorityOrder.put((char) ('A' + i), i + 27);
        }

        for (int i = 0; i < data.size(); i++) {
            totalPriorities += findBadges(new String[]{data.get(i), data.get(i + 1), data.get(i + 2)},
                    priorityOrder);
            i += 2;
        }

        System.out.println("Part 2: " + totalPriorities);
    }

    public static int findBadges(String[] group, HashMap<Character, Integer> a) {

        for (int i = 0; i < group[0].length(); i++) {
            for (int j = 0; j < group[1].length(); j++) {
                for (int k = 0; k < group[2].length(); k++) {
                    if (group[0].charAt(i) == group[1].charAt(j) && group[1].charAt(j) == group[2].charAt(k)) {
                        if (a.containsKey(group[2].charAt(k))) {
                            return a.get(group[2].charAt(k));
                        }
                    }
                }
            }
        }

        return 0;
    }
}