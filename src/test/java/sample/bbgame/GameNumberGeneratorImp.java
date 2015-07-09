package sample.bbgame;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.fail;

/**
 * Created by ci on 2015-06-29.
 */
public class GameNumberGeneratorImp implements GameNumberGenerator {
    @Override
    public String generate() {
        ArrayList<String> list = new ArrayList<String>();
        int listSize = list.size();

        for (int i = 0; i < 9; i++) {
            list.add((i + 1) + "");
        }

        int numberCount = 3;
        String gameNumber = "";
        for (int i = 0; i < numberCount; i++){
            listSize = list.size();
            int randomIdx = (int) (Math.random() * listSize);
            gameNumber += list.get(randomIdx);
            list.remove(randomIdx);
        }

        return gameNumber;
    }

    @Test
    public void generateRandomNum_throwIllegalEx() {
        String gameNumber = generate();
        // assertIllegalNumThrowEx(gameNumber);
    }

    public void assertIllegalNumThrowEx(String randomNum) {
        try {
            assertGuessNumberValid(randomNum);
            fail();
        } catch (IllegalArgumentException e) {

        }
    }

    public void assertGuessNumberValid(String randomNum) {
        if (randomNum == null) throw new IllegalArgumentException();
        if (randomNum.length() != 3) throw new IllegalArgumentException();
        for (char ch : randomNum.toCharArray())
            if (ch < '0' || ch > '9') throw new IllegalArgumentException();

        if (randomNum.charAt(0) == randomNum.charAt(1) || randomNum.charAt(0) == randomNum.charAt(2)
                || randomNum.charAt(1) == randomNum.charAt(2))
            throw new IllegalArgumentException();
    }
}
