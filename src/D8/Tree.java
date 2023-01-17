package D8;

public class Tree {

    boolean visibility;
    int scenicScore;

    public Tree(boolean visibility, int scenicScore) {
        this.visibility = visibility;
        this.scenicScore = scenicScore;
    }

    public boolean getVisibility() {
        return visibility;
    }

    public int getScenicScore() {
        return scenicScore;
    }
}
