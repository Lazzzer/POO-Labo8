package engine;

import chess.ChessController;
import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;
import engine.piece.*;
import engine.rule.CheckRule;
import engine.rule.PromotionRule;

public class ChessEngine implements ChessController {
    private static final int BOARD_SIZE = 8;

    private ChessView view;
    private GameState gameState;
    
    private void drawBoard() {
        for (int i = 0; i < BOARD_SIZE; ++i) {
            for (int j = 0; j < BOARD_SIZE; ++j) {
                if (gameState.getPiece(i, j) != null) {
                    view.putPiece(gameState.getPiece(i, j).getPieceType(), gameState.getPiece(i, j).getColor(), j, i);
                } else {
                    view.removePiece(j, i);
                }
            }
        }
    }

    private Piece promoteWithInput(int toX, int toY) {
        return view.askUser("Pawn Promotion", "What do you pick?",
                new Rook(gameState.getPiece(toY, toX).getColor()),
                new Knight(gameState.getPiece(toY, toX).getColor()),
                new Bishop(gameState.getPiece(toY, toX).getColor()),
                new Queen(gameState.getPiece(toY, toX).getColor()));
    }

    @Override
    public void start(ChessView view) {
        this.view = view;
        view.startView();
    }

    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        boolean goodTurn = false;
        if (gameState.getPiece(fromY, fromX) != null && gameState.getPiece(fromY, fromX).getColor() == gameState.getTurn()
                && !(fromX == toX && fromY == toY)) {
            gameState.createBoardMovement(fromY, fromX, toY, toX);
            if (gameState.getPiece(fromY, fromX).move(gameState, fromX, fromY, toX, toY)) {
                gameState.movePiece(fromY, fromX, toY, toX);
                if (!CheckRule.isChecked(gameState.getTurn(), gameState,
                        gameState.getKingCoords(gameState.getTurn())) ) {
                    if (!gameState.isChecked && gameState.getPiece(toY, toX).getPieceType() == PieceType.PAWN) {
                        if (PromotionRule.canPromote(gameState.getTurn(), gameState, toY))
                            gameState.setPiece(promoteWithInput(toX, toY), toY, toX);
                    }
                    gameState.isChecked = CheckRule.isChecked(gameState.getNextTurn(), gameState,
                            gameState.getKingCoords(gameState.getNextTurn()));
                    gameState.switchTurn();
                    goodTurn = true;
                } else{
                    gameState.revertMoves();
                }
            }
        }
        endTurn(gameState.isChecked);
        return goodTurn;
    }
    
    private void displayTurn(){
        displayTurn(false);
    }
    private void displayTurn(boolean check){
        view.displayMessage("Au tour des " + gameState.getTurn() + (check? " / Echec" : ""));
    }
    
    private void endTurn(boolean check){
        displayTurn(check);
        gameState.removedMovedPieces();
    }

    @Override
    public void newGame() {
        gameState = new GameState(testCastlingWithChecks(), BOARD_SIZE, PlayerColor.WHITE, view);
        drawBoard();
        displayTurn();
    }

    private Piece[][] populateBoard() {
        int position = 0;

        Piece[][] board = new Piece[BOARD_SIZE][BOARD_SIZE];

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

        return board;
    }

    private Piece[][] test() {
        Piece[][] board = new Piece[BOARD_SIZE][BOARD_SIZE];

        board[0][0] = new King(PlayerColor.WHITE);
        board[0][7] = new King(PlayerColor.BLACK);

        return board;
    }

    private Piece[][] testPromotionWithChecks() {
        Piece[][] board = new Piece[BOARD_SIZE][BOARD_SIZE];

        board[BOARD_SIZE - 2][0] = new Pawn(PlayerColor.WHITE);
        board[BOARD_SIZE - 3][0] = new King(PlayerColor.BLACK);
        board[BOARD_SIZE - 2][4] = new King(PlayerColor.WHITE);

        return board;
    }

    private Piece[][] testEnPassantWithChecks() {
        Piece[][] board = new Piece[BOARD_SIZE][BOARD_SIZE];

        board[2][3] = new King(PlayerColor.WHITE);
        board[3][3] = new Pawn(PlayerColor.WHITE);
        board[7][7] = new King(PlayerColor.BLACK);
        board[5][3] = new Queen(PlayerColor.BLACK);
        board[6][2] = new Pawn(PlayerColor.BLACK);

        return board;
    }

    private Piece[][] testDoubleForwardWithChecks() {
        Piece[][] board = new Piece[BOARD_SIZE][BOARD_SIZE];

        board[0][4] = new King(PlayerColor.WHITE);
        board[1][3] = new Pawn(PlayerColor.WHITE);
        board[7][7] = new King(PlayerColor.BLACK);
        board[3][1] = new Queen(PlayerColor.BLACK);

        return board;
    }
    
    private Piece[][] testEnPassant() {
        Piece[][] board = new Piece[BOARD_SIZE][BOARD_SIZE];
        board[7][0] = new King(PlayerColor.WHITE);
        board[7][7] = new King(PlayerColor.BLACK);
    
        board[1][0] = new Pawn(PlayerColor.WHITE);
        board[1][2] = new Pawn(PlayerColor.WHITE);
        board[4][5] = new Pawn(PlayerColor.WHITE);
    
        board[6][4] = new Pawn(PlayerColor.BLACK);
        board[6][6] = new Pawn(PlayerColor.BLACK);
        board[3][1] = new Pawn(PlayerColor.BLACK);
        
        return board;
    }

    private Piece[][] testCastlingWithChecks() {
        Piece[][] board = new Piece[BOARD_SIZE][BOARD_SIZE];

        // Tours
        board[0][0] = new Rook(PlayerColor.WHITE);
        board[0][BOARD_SIZE - 1] = new Rook(PlayerColor.WHITE);
        board[BOARD_SIZE - 1][0] = new Rook(PlayerColor.BLACK);
        board[BOARD_SIZE - 1][BOARD_SIZE - 1] = new Rook(PlayerColor.BLACK);
        
        board[3][BOARD_SIZE - 2] = new Pawn(PlayerColor.WHITE);

        // Reines et Rois
        board[0][3] = new Queen(PlayerColor.WHITE);
        board[0][BOARD_SIZE - 1 - 3] = new King(PlayerColor.WHITE);
        board[BOARD_SIZE - 1][3] = new Queen(PlayerColor.BLACK);
        board[BOARD_SIZE - 1][BOARD_SIZE - 1 - 3] = new King(PlayerColor.BLACK);

        return board;
    }
}
