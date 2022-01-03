package engine;

import chess.ChessController;
import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;
import engine.piece.*;
import engine.rule.CheckRule;
import engine.rule.PromotionRule;

public class ChessEngine implements ChessController {
    private ChessView view;

    static final int BOARD_SIZE = 8;
    Piece[][] board;
    PlayerColor turn;
    PlayerColor nextTurn;

    private void populateBoard() {
        int position = 0;

        // Tours
        board[0][position] = new Rook(PlayerColor.WHITE);
        board[0][BOARD_SIZE - 1 - position] = new Rook(PlayerColor.WHITE);
        board[BOARD_SIZE - 1][position] = new Rook(PlayerColor.BLACK);
        board[BOARD_SIZE - 1][BOARD_SIZE - 1 - position] = new Rook(PlayerColor.BLACK);

        position++;
        // Cavaliers
        board[0][position] = new Knight(PlayerColor.WHITE);
        board[0][BOARD_SIZE - 1 - position] = new Knight(PlayerColor.WHITE);
        board[BOARD_SIZE - 1][position] = new Knight(PlayerColor.BLACK);
        board[BOARD_SIZE - 1][BOARD_SIZE - 1 - position] = new Knight(PlayerColor.BLACK);

        position++;
        // Fous
        board[0][position] = new Bishop(PlayerColor.WHITE);
        board[0][BOARD_SIZE - 1 - position] = new Bishop(PlayerColor.WHITE);
        board[BOARD_SIZE - 1][position] = new Bishop(PlayerColor.BLACK);
        board[BOARD_SIZE - 1][BOARD_SIZE - 1 - position] = new Bishop(PlayerColor.BLACK);

        position++;
        // Reines et Rois
        board[0][position] = new Queen(PlayerColor.WHITE);
        board[0][BOARD_SIZE - 1 - position] = new King(PlayerColor.WHITE);
        board[BOARD_SIZE - 1][position] = new Queen(PlayerColor.BLACK);
        board[BOARD_SIZE - 1][BOARD_SIZE - 1 - position] = new King(PlayerColor.BLACK);

        // Pions
        for (int i = 0; i < BOARD_SIZE; ++i) {
            board[1][i] = new Pawn(PlayerColor.WHITE);
            board[BOARD_SIZE - 2][i] = new Pawn(PlayerColor.BLACK);
        }
    }

    private void drawBoard() {
        for (int i = 0; i < BOARD_SIZE; ++i) {
            for (int j = 0; j < BOARD_SIZE; ++j) {
                if (board[i][j] != null) {
                    view.putPiece(board[i][j].getPieceType(), board[i][j].getColor(), j, i);
                } else {
                    view.removePiece(j, i);
                }
            }
        }
    }

    private void switchTurn() {
        turn = turn == PlayerColor.WHITE ? PlayerColor.BLACK : PlayerColor.WHITE;
        nextTurn = turn == PlayerColor.WHITE ? PlayerColor.BLACK : PlayerColor.WHITE;
    }

    private Piece promoteWithInput(int toX, int toY) {
        return view.askUser("Pawn Promotion", "What do you pick?",
                new Rook(board[toY][toX].getColor()),
                new Knight(board[toY][toX].getColor()),
                new Bishop(board[toY][toX].getColor()),
                new Queen(board[toY][toX].getColor()));
    }

    @Override
    public void start(ChessView view) {
        this.view = view;
        view.startView();
    }

    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        if (board[fromY][fromX] == null || board[fromY][fromX].getColor() != turn) {
            return false;
        } else if (board[fromY][fromX].move(board, fromX, fromY, toX, toY)) {

            var tempPiece = board[toY][toX];
            board[toY][toX] = board[fromY][fromX];
            board[fromY][fromX] = null;

            if (CheckRule.isChecked(turn, board)) {
                board[fromY][fromX] = board[toY][toX];
                board[toY][toX] = tempPiece;
                view.displayMessage("CHECK");
                return false;
            }

            if (board[toY][toX].getPieceType() == PieceType.PAWN) {
                if (PromotionRule.canPromote(turn, board, toY))
                    board[toY][toX] = promoteWithInput(toX, toY);
            }

            if (CheckRule.isChecked(nextTurn, board))
                view.displayMessage("CHECK");

            drawBoard();
            switchTurn();
            return true;
        }
        return false;
    }

    @Override
    public void newGame() {
        board = new Piece[BOARD_SIZE][BOARD_SIZE];
        populateBoard();
//        board[BOARD_SIZE - 2][0] = new Pawn(PlayerColor.WHITE);
//        board[BOARD_SIZE - 3][0] = new King(PlayerColor.BLACK);
//        board[BOARD_SIZE - 2][4] = new King(PlayerColor.WHITE);

        drawBoard();
        turn = PlayerColor.WHITE;
        nextTurn = PlayerColor.BLACK;
    }
}
