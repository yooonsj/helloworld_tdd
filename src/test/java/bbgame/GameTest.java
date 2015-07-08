package bbgame;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by ci on 2015-06-23.
 */
public class GameTest {

    private Game game;

    @Before
    public void setUp() throws Exception {
        game = new Game();
    }

    @Test
    public void givenInvalidGuessNumber_throwIllegalArgEx(){
        assertIllegalArgExThrown(null);
        assertIllegalArgExThrown("12");

        assertIllegalArgExThrown("12a");
        assertIllegalArgExThrown("a45");

        assertIllegalArgExThrown("113");
        assertIllegalArgExThrown("011");
        assertIllegalArgExThrown("141");
    }

    private void assertIllegalArgExThrown(String guessNum) {
        try {
            game.guess(guessNum);
            fail();
        }catch(IllegalArgumentException e){
        }
    }

    @Test
    public void givenExactMatchingGuessNum_returnSolvedResult() {
        generateGameNumber();
        assertSolvedGuessResult(game.getNumber());

        generateGameNumber();
        assertSolvedGuessResult(game.getNumber());

        //assertNonSolvedResult("789", 0, 0);
    }

    private void assertSolvedGuessResult(String guessNum) {
        assertGuessResult(guessNum, true, 3, 0);
    }

    private void generateGameNumber() {
        GameNumberGenerator gameNumberGeneratorImp = new GameNumberGeneratorImp();
        game.setGameNumberGenerator(gameNumberGeneratorImp);
        game.generateNumber();
    }

    private void assertGuessResult(String guessNum, boolean solved, int strikes, int balls) {
        GuessResult guessResult = game.guess(guessNum);
        assertThat(guessResult.isSolved(), equalTo(solved));
        assertThat(guessResult.getStrikes(), equalTo(strikes));
        assertThat(guessResult.getBalls(), equalTo(balls));
    }

    @Test
    public void givenSomeMatchingGuessNum_returnNonSolvedResult() {
        generateGameNumber();

        assertNonSolvedResult("024", 1, 0);
        assertNonSolvedResult("103", 2, 0);
        assertNonSolvedResult("124", 2, 0);
        assertNonSolvedResult("023", 2, 0);

        assertNonSolvedResult("204", 0, 1);
        assertNonSolvedResult("035", 0, 1);
        assertNonSolvedResult("314", 0, 2);

        assertNonSolvedResult("132", 1, 2);
        assertNonSolvedResult("134", 1, 1);
        assertNonSolvedResult("321", 1, 2);
    }

    private void assertNonSolvedResult(String guessNum, int strikes, int balls) {
        assertGuessResult(guessNum, false, strikes, balls);
    }

}
