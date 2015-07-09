package sample.bbgame;

/**
 * Created by ci on 2015-06-29.
 */
class GuessResult {
    private boolean solved;
    private int strikes;
    private int balls;

    public GuessResult(boolean solved, int strikes, int balls) {
        this.solved = solved;
        this.strikes = strikes;
        this.balls = balls;
    }

    public boolean isSolved() {
        return solved;
    }

    public int getStrikes() {
        return strikes;
    }

    public int getBalls() {
        return balls;
    }
}
