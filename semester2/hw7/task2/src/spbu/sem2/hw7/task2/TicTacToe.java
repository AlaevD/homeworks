package spbu.sem2.hw7.task2;

public class TicTacToe {
    private static final int GAME_SIZE = 3;
    private static final char EMPTY = ' ';
    private boolean gameOver = false;
    private char currentTurn = 'X';
    /** how many possible turns player can make */
    private int turnsLeft = GAME_SIZE * GAME_SIZE;
    /** game field */
    private char[][] field = new char[GAME_SIZE][GAME_SIZE];
    private String gameResult;

    /** Either 'X' or 'O' */
    private char winner;

    /**
     * @return game size
     */
    public static int getGameSize() {
        return GAME_SIZE;
    }

    /**
     * @return game result
     */
    public String getGameResult() {
        return gameResult;
    }

    public TicTacToe() {
        resetGame();
    }


    /** resets game state to the beginning */
    private void resetGame() {
        gameOver = false;
        currentTurn = 'X';
        turnsLeft = GAME_SIZE * GAME_SIZE;
        for (int i = 0; i < GAME_SIZE; i++) {
            for (int j = 0; j < GAME_SIZE; j++) {
                field[i][j] = EMPTY;
            }
        }
        gameResult = "";
        winner = EMPTY;
    }

    /**
     * processes game turn in given cell
     * @param i cell row index
     * @param j cell column index
     * @return symbol placed in cell or space if turn was not made
     */
    public char makeTurn(int i, int j) {
        if (field[i][j] != EMPTY || gameOver) {
            return EMPTY;
        }

        field[i][j] = currentTurn;
        changeTurn();
        turnsLeft--;
        checkForEnd(i, j);
        return field[i][j];
    }

    /**
     * checks if game is over(either with a tie or win).
     * Checks all possible combinations with given cell
     * @param lastTurnI row index of given cell
     * @param lastTurnJ column index of given cell
     */
    private void checkForEnd(int lastTurnI, int lastTurnJ) {
        if (gameIsWon(lastTurnI, lastTurnJ)) {
            gameOver = true;
            gameResult = String.valueOf(winner) + " wins";
        }
        else if (turnsLeft == 0) {
            gameOver = true;
            gameResult = "tie";
        }
    }

    /**
     * @return true if game is over
     */
    public boolean gameOver() {
        return gameOver;
    }

    /**
     * checks if game is won with combo containing given cell
     * @param lastTurnI row index of given cell
     * @param lastTurnJ column index of given cell
     * @return true if game is won
     */
    private boolean gameIsWon(int lastTurnI, int lastTurnJ) {
        boolean result = false;
        for (int i = lastTurnI - GAME_SIZE + 1; i <= lastTurnI; i++) {
            result |= checkLine(i, lastTurnJ, 1, 0);
        }
        for (int j = lastTurnJ - GAME_SIZE + 1; j <= lastTurnJ; j++) {
            result |= checkLine(lastTurnI, j, 0, 1);
        }

        int i = lastTurnI - GAME_SIZE + 1;
        int j = lastTurnJ - GAME_SIZE + 1;
        while (i != lastTurnI + 1) {
            result |= checkLine(i, j, 1, 1);
            i++;
            j++;
        }

        i = lastTurnI - GAME_SIZE + 1;
        j = lastTurnJ + GAME_SIZE - 1;
        while (i != lastTurnI + 1) {
            result |= checkLine(i, j, 1, -1);
            i++;
            j--;
        }

        return result;
    }

    /**
     * checks line(either horizontal or vertical or diagonal) for game win
     * @param startI start row index
     * @param startJ start column index
     * @param deltaI delta row
     * @param deltaJ delta column
     * @return true if game is won
     */
    private boolean checkLine(int startI, int startJ, int deltaI, int deltaJ) {
        if (!inside(startI, startJ)) {
            return false;
        }

        char currentSymbol = field[startI][startJ];
        int i = startI + deltaI;
        int j = startJ + deltaJ;
        for (int cnt = 0; cnt < GAME_SIZE - 1; cnt++) {
            if (!inside(i, j)) {
                return false;
            }
            if (field[i][j] != currentSymbol) {
                return false;
            }
            i += deltaI;
            j += deltaJ;
        }
        winner = currentSymbol;
        return true;
    }

    /**
     * checks if given cell is inside of game field
     * @param i cell row index
     * @param j cell column index
     * @return true if cell is inside
     */
    private boolean inside(int i, int j) {
        return 0 <= i && i < GAME_SIZE && 0 <= j && j < GAME_SIZE;
    }

    /** changes game turn */
    private void changeTurn() {
        currentTurn = (currentTurn == 'X' ? 'O' : 'X');
    }
}
