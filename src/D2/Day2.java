package D2;

import java.util.List;

import static common.PuzzleReader.readPuzzle;

public class Day2 {

    public static void main(String[] args) {

        List<String> data = readPuzzle("src/D2/input.txt");
        assert data != null;

        part1(data);
        part2(data);
    }

    static void part1(List<String> data) {
        int score = 0;

        for (String s : data) {
            String[] selection = s.split(" ");

            int opponent = calculePoints(selection[0]);
            int you = calculePoints(selection[1]);

            //draw case
            if (you == opponent) {
                score += 3 + you;
            }

            //win cases
            //rock and scissors
            if (you == 1 && opponent == 3) score += 6 + you;
            //paper and rock
            if (you == 2 && opponent == 1) score += 6 + you;
            //scissors and paper
            if (you == 3 && opponent == 2) score += 6 + you;

            //lose cases
            //rock and scissors
            if (you == 3 && opponent == 1) score += you;
            //paper and rock
            if (you == 1 && opponent == 2) score += you;
            //scissors and paper
            if (you == 2 && opponent == 3) score += you;
        }
        System.out.println("Score part 1: " + score);
    }

    static void part2(List<String> data) {

        int rock = 1;
        int paper = 2;
        int scissors = 3;
        int score = 0;
        for (String s : data) {

            String[] selection = s.split(" ");

            int opponent = calculePoints(selection[0]);
            int strategy = calculePoints(selection[1]);

            //case rock
            // lose
            if (opponent == 1 && strategy == 1) score += scissors;
            // draw
            if (opponent == 1 && strategy == 2) score += 3 + rock;
            // win
            if (opponent == 1 && strategy == 3) score += 6 + paper;

            //case paper
            // lose
            if (opponent == 2 && strategy == 1) score += rock;
            // draw
            if (opponent == 2 && strategy == 2) score += 3 + paper;
            // win
            if (opponent == 2 && strategy == 3) score += 6 + scissors;

            //case scissors
            // lose
            if (opponent == 3 && strategy == 1) score += paper;
            // draw
            if (opponent == 3 && strategy == 2) score += 3 + scissors;
            // win
            if (opponent == 3 && strategy == 3) score += 6 + rock;

        }

        System.out.println("score part 2: " + score);
    }

    static int calculePoints(String selection) {

        int points = 0;

        switch (selection) {
            case "A":
            case "X":
                //rock
                points = 1;
                break;
            case "B":
            case "Y":
                //paper
                points = 2;
                break;
            case "C":
            case "Z":
                //scissors
                points = 3;
                break;
        }
        return points;
    }
}