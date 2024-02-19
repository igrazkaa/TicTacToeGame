public class Board {
    public Cell[][] board;
    public static final int ROWS = 3;
    public static final int COLS = 3;

    public Board() {
        initGame();
    }

    private void initGame() {
        board = new Cell[ROWS][COLS];
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                board[row][col] = new Cell(row, col);
            }
        }
    }

    public void newGame(){
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                board[row][col].newGame(); // init each cell of the board
            }
        }
    }

    public void displayBoard(){
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                System.out.print(" ");
                board[row][col].displayCell();
                System.out.print(" ");
                if (col < COLS - 1) System.out.print("|");
            }
            System.out.print("\n");
            if (row < ROWS - 1) System.out.println("-----------");
        }
        System.out.print("\n");
    }

    public State gameStep(Seed player, int selectedRow, int selectedCol) {
        board[selectedRow][selectedCol].content = player;

        if (
            board[selectedRow][0].content == player && board[selectedRow][1].content == player && board[selectedRow][2].content == player // 3-in-the-row
            ||
            board[0][selectedCol].content == player && board[1][selectedCol].content == player && board[2][selectedCol].content == player // 3-in-the-column
            ||
            selectedRow == selectedCol && board[0][0].content == player && board[1][1].content == player && board[2][2].content == player // 3-in-the-diagonal
            ||
            selectedRow + selectedCol == 2 && board[2][0].content == player && board[1][1].content == player && board[0][2].content == player) // 3-in-the-opposite-diagonal
        {
            return (player == Seed.CROSS) ? State.CROSS_WON : State.NOUGHT_WON;
        } else {
            for (int row = 0; row < ROWS; ++row) {
                for (int col = 0; col < COLS; ++col) {
                    if (board[row][col].content == Seed.NO_SEED) {
                        return State.PLAYING; // continue playing if there is an empty cell
                    }
                }
            }
            return State.DRAW; // no empty cell & no winning ==> DRAW
        }
    }

}
