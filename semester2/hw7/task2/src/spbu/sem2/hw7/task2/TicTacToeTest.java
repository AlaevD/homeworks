package spbu.sem2.hw7.task2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.TooManyListenersException;

import static org.junit.Assert.*;

/** TicTacToe class test */
public class TicTacToeTest {
    private TicTacToe game;
    @Before
    public void initialize() {
        game = new TicTacToe();
    }

    /** game created successfully test */
    @Test
    public void initialTest() {
        Assert.assertTrue(game.getGameResult().isEmpty());
        Assert.assertFalse(game.gameOver());
        Assert.assertTrue(game.getGameSize() == 3);
    }

    private char changeTurn(char c) {
        return (c == 'X' ? 'O' : 'X');
    }

    /** checks that turns are made properly */
    @Test
    public void makeTurnTest() {
        int i = 0;
        int j = 0;
        char expectedTurn = 'X';
        for (int cnt = 0; cnt < 3; cnt++) {
            Assert.assertTrue(expectedTurn == game.makeTurn(i, j));
            expectedTurn = changeTurn(expectedTurn);
            j++;
        }
        i = 2;
        j = 0;
        for (int cnt = 0; cnt < 3; cnt++) {
            Assert.assertTrue(expectedTurn == game.makeTurn(i, j));
            expectedTurn = changeTurn(expectedTurn);
            j++;
        }
        i = 1;
        j = 0;
        for (int cnt = 0; cnt < 3; cnt++) {
            Assert.assertTrue(expectedTurn == game.makeTurn(i, j));
            expectedTurn = changeTurn(expectedTurn);
            j++;
        }
        Assert.assertTrue(game.gameOver());
        Assert.assertTrue(game.getGameResult().contains("tie"));
    }

    /** checks that game is won when necessary */
    @Test
    public void gameIsWonTest() {
        int i = 0;
        int j = 0;
        for (int cnt = 0; cnt < 5; cnt++) {
            game.makeTurn(i, j);
            i = (cnt + 1) % 2;
            j = (cnt + 1) / 2;
        }
        Assert.assertTrue(game.gameOver());
        Assert.assertTrue(game.getGameResult().contains("win"));
    }

}