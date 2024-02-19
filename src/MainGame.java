import java.util.Scanner;

public class MainGame {
    private Board currentBoard;
    private State currentState;
    private Seed currentPlayer;

    public MainGame(){
        initGame();
        newGame();

        while(currentState == State.PLAYING){
            playerMove();
            currentBoard.displayBoard();

            switch (currentState) {
                case CROSS_WON -> System.out.println("Player X has won!");
                case NOUGHT_WON -> System.out.println("Player O has won!");
                case DRAW -> System.out.println("TIE GAME! Nobody has won!");
                default -> currentState = State.PLAYING;
            }

            currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
        }
    }

    public void initGame() {
        System.out.println("Welcome to Tic Tac Toe Game!");
        currentBoard = new Board();
    }

    public void newGame(){
        currentBoard.newGame();
        currentPlayer = Seed.CROSS;
        currentState = State.PLAYING;

        boolean correctInput = false;
        while(!correctInput) {
            System.out.println("Who is first? (x/o): ");
            Scanner in = new Scanner(System.in);
            String answer = in.nextLine();
            switch (answer) {
                case "x" -> {
                    currentPlayer = Seed.CROSS;
                    correctInput = true;
                }
                case "o" -> {
                    currentPlayer = Seed.NOUGHT;
                    correctInput = true;
                }
                default -> System.out.println("Invalid char, please, try again...");
            }
        }
    }

    public void playerMove(){
        boolean correctInput = false;
        int row = -1;
        int col = -1;

        while(!correctInput) {
            String sign = currentPlayer.getSign();
            System.out.println("Player " + sign + ", choose a cell (row[1-3] /enter/ column[1-3]): ");
            Scanner in = new Scanner(System.in);
            try {
                row = in.nextInt() - 1;
                col = in.nextInt() - 1;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input, integers only. Please, try again...");
                continue;
            }

            if (row >= 0 && row < Board.ROWS && col >= 0 && col < Board.COLS && currentBoard.board[row][col].content == Seed.NO_SEED) {
                currentState = currentBoard.gameStep(currentPlayer, row, col);
                correctInput = true;
            } else if (row >= 0 && row < Board.ROWS && col >= 0 && col < Board.COLS && currentBoard.board[row][col].content != Seed.NO_SEED) {
                System.out.println("Sorry, the cell at (" + (row + 1) + "," + (col + 1) + ") is already occupied. Please, choose again...");
            } else {
                System.out.println("Sorry, (" + (row + 1) + "," + (col + 1) + ") is outside the available range. Please, try again...");
            }
        }
    }
}
