package D5;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import static common.PuzzleReader.readPuzzle;

public class Day5 {

    public static void main(String[] args) {
        List<String> data = readPuzzle("src/D5/input.txt");
        assert data != null;

        List<ArrayDeque<String>> boxsPart1 = getBox(data);
        List<ArrayDeque<String>> boxsPart2 = getBox(data);
        List<String> moves = getMoves(data);

        part1(boxsPart1, moves);
        System.out.println("-----");
        part2(boxsPart2, moves);
    }

    private static void part2(List<ArrayDeque<String>> boxs, List<String> moves) {
        int mov;
        int from;
        int to;

        for (String move : moves) {

            move = move.replace("move ", "");
            move = move.replace(" from", "");
            move = move.replace(" to", "");

            String[] extract = move.split(" ");

            mov = Integer.parseInt(extract[0]);
            from = Integer.parseInt(extract[1]);
            to = Integer.parseInt(extract[2]);

            String[] box = new String[mov];
            for (int i = 0; i < mov; i++) {
                box[i] = boxs.get(from - 1).pop();
            }

            for (int i = mov; i > 0; i--) {
                boxs.get(to - 1).addFirst(box[i - 1]);
            }
        }

        StringBuilder boxUpOnTopStack = new StringBuilder();
        for (ArrayDeque<String> stack : boxs) {
            boxUpOnTopStack.append(stack.peekFirst());
        }

        System.out.println("After the rearrangement procedure completes, crates ends up on top of each stack are: "
                + boxUpOnTopStack);
    }

    private static void part1(List<ArrayDeque<String>> boxs, List<String> moves) {

        int mov;
        int from;
        int to;

        for (String move : moves) {

            move = move.replace("move ", "");
            move = move.replace(" from", "");
            move = move.replace(" to", "");

            String[] extract = move.split(" ");

            mov = Integer.parseInt(extract[0]);
            from = Integer.parseInt(extract[1]);
            to = Integer.parseInt(extract[2]);

            for (int i = 0; i < mov; i++) {

                boxs.get(to - 1).addFirst(boxs.get(from - 1).pop());
            }
        }

        StringBuilder boxUpOnTopStack = new StringBuilder();
        for (ArrayDeque<String> stack : boxs) {
            boxUpOnTopStack.append(stack.peekFirst());
        }

        System.out.println("what crate ends up on top of each stack?: " + boxUpOnTopStack);
    }

    private static List<ArrayDeque<String>> getBox(List<String> data) {
        boolean createStackList = false;
        List<ArrayDeque<String>> stackList = new ArrayList<>();

        for (String s : data) {
            if (s.equals("")) break;

            if (s.startsWith(" ")) {
                s = s.replace("]    ", "] [0]");
                s = s.replace("    ", "[0] ");
            }

            s = s.replace("    ", " [0]");
            s = s.replace("[", "");
            s = s.replace("]", "");
            s = s.replace(" ", "");

            String[] positions = s.split("");

            if (!createStackList) {
                createStackList = true;
                for (int i = 0; i < positions.length; i++) {
                    stackList.add(new ArrayDeque<>());
                }
            }

            for (int i = 0; i < stackList.size(); i++) {
                if (!positions[i].equals("0")) {
                    stackList.get(i).addLast(positions[i]);

                }
            }
        }

        return stackList;
    }

    private static List<String> getMoves(List<String> data) {
        boolean movesB = false;
        List<String> movements = new ArrayList<>();

        for (String s : data) {
            if (s.equals("")) movesB = true;
            if (movesB) {
                movements.add(s);
            }
        }
        movements.removeIf(x -> x.equals(""));
        return movements;
    }
}