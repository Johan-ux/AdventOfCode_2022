package D8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

import static common.PuzzleReader.readPuzzle;

public class Day8 {

    static BiFunction<Integer, Integer, Boolean> visibility = (x, y) -> x < y;

    public static void main(String[] args) {

        List<String> data = readPuzzle("src/D8/input.txt");
        assert data != null;
        int[][] newData = manageData(data);

        part1(newData);
    }

    private static void part1(int[][] newData) {

        int trees = (newData.length * 2) + ((newData[0].length - 2) * 2);
        int scenicScore = 0;

        for (int i = 1; i < newData.length - 1; i++) {
            for (int j = 1; j < newData[i].length - 1; j++) {

                Tree tree = (isVisible(i, j, newData));
                if (tree.getVisibility()) {
                    trees++;
                }

                if (tree.getScenicScore() > scenicScore) {
                    scenicScore = tree.getScenicScore();
                }
            }
        }

        //trees visible on the edge
        System.out.println("visible trees: " + trees);
        System.out.println("highest scenic score: " + scenicScore);
    }

    private static int[][] manageData(List<String> data) {

        int[][] newData = new int[data.get(0).length()][data.size()];
        for (int i = 0; i < newData.length; i++) {
            for (int j = 0; j < newData[i].length; j++) {

                String[] input = data.get(i).split("");
                newData[i][j] = Integer.parseInt(input[j]);
            }
        }
        return newData;
    }

    private static Tree isVisible(int vertical, int horizontal, int[][] array) {
        //true visible
        //false not visible
        List<Tree> treeVisible = new ArrayList<>();

        treeVisible.add(left(vertical, horizontal, array));
        treeVisible.add(right(vertical, horizontal, array));
        treeVisible.add(up(vertical, horizontal, array));
        treeVisible.add(down(vertical, horizontal, array));

        return new Tree(treeVisible.stream().anyMatch(Tree::getVisibility), scenicScore(treeVisible));
    }

    private static Tree left(int vertical, int horizontal, int[][] array) {

        List<Boolean> treeVisible = new ArrayList<>();
        for (int i = vertical; i == vertical; i++) {
            for (int j = 0; j < horizontal; j++) {
                treeVisible.add(visibility.apply(array[i][j], array[vertical][horizontal]));
            }
        }
        return new Tree(!treeVisible.contains(false), countTrees(treeVisible));
    }

    private static Tree right(int vertical, int horizontal, int[][] array) {

        List<Boolean> treeVisible = new ArrayList<>();

        for (int i = vertical; i == vertical; i--) {
            for (int j = array[i].length - 1; j > horizontal; j--) {
                treeVisible.add(visibility.apply(array[i][j], array[vertical][horizontal]));
            }
        }
        return new Tree(!treeVisible.contains(false), countTrees(treeVisible));
    }

    private static Tree up(int vertical, int horizontal, int[][] array) {

        List<Boolean> treeVisible = new ArrayList<>();

        for (int i = 0; i < vertical; i++) {
            for (int j = horizontal; j == horizontal; j++) {
                treeVisible.add(visibility.apply(array[i][j], array[vertical][horizontal]));
            }
        }
        return new Tree(!treeVisible.contains(false), countTrees(treeVisible));
    }

    private static Tree down(int vertical, int horizontal, int[][] array) {

        List<Boolean> treeVisible = new ArrayList<>();

        for (int i = array.length - 1; i > vertical; i--) {
            for (int j = horizontal; j == horizontal; j--) {
                treeVisible.add(visibility.apply(array[i][j], array[vertical][horizontal]));
            }
        }
        return new Tree(!treeVisible.contains(false), countTrees(treeVisible));
    }

    private static int countTrees(List<Boolean> trees) {

        int count = 0;
        for (Boolean tree : trees) {
            if (tree) {
                count++;
            } else {
                count = 1;
            }
        }
        return count;
    }

    private static int scenicScore(List<Tree> treeVisible) {

        int[] score = new int[4];
        for (int i = 0; i < treeVisible.size(); i++) {
            score[i] = treeVisible.get(i).getScenicScore();
        }
        return Arrays.stream(score).reduce(1, (a, b) -> a * b);
    }
}
