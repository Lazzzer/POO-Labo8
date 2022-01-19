package engine;

import chess.ChessController;
import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;
import engine.piece.*;
import engine.rule.CheckRule;
import engine.rule.PromotionRule;

/**
 * Classe représentant un gestionnaire de partie d'échecs
 * @author Alexandre Jaquier
 * @author Lazar Pavicevic
 */
public class ChessEngine implements ChessController {
    private static final int BOARD_SIZE = 8;

    private ChessView view;
    private GameState gameState;

    /**
     * Initialise et démarre une view
     * @param view la vue à utiliser
     */
    @Override
    public void start(ChessView view) {
        this.view = view;
        view.startView();
    }

    /**
     * Démarre une nouvelle partie
     */
    @Override
    public void newGame() {
        gameState = new GameState(testCastlingWithChecks(), PlayerColor.WHITE, view);
        drawBoard();
        displayTurn(false);
    }

    /**
     * Contrôle si le déplacement d'une pièce vers une case données est légal ou non
     * @param fromX Colonne de départ
     * @param fromY Ligne de départ
     * @param toX   Colonne d'arrivée
     * @param toY   Ligne d'arrivée
     * @return Vrai si le mouvement est légal
     */
    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        boolean goodTurn = false;
        if (!gameState.isEndGame() && gameState.getPiece(fromY, fromX) != null && gameState.getPiece(fromY, fromX).getColor() == gameState.getTurn()
                && !(fromX == toX && fromY == toY)) {
            gameState.createBoardMovement(fromY, fromX, toY, toX);
            if (gameState.getPiece(fromY, fromX).move(gameState, fromX, fromY, toX, toY)) {
                gameState.movePiece(fromY, fromX, toY, toX);
                if (!CheckRule.isChecked(gameState.getTurn(), gameState, gameState.getKingCoords(gameState.getTurn()))) {
                    // Si le mouvement est valide, l'historique peut être effacé
                    gameState.removeMovedPieces();

                    // Contrôle si une promotion peut être faite
                    if (gameState.getPiece(toY, toX).getPieceType() == PieceType.PAWN) {
                        if (PromotionRule.canPromote(gameState.getTurn(), gameState, toY))
                            gameState.setPiece(promoteWithInput(toX, toY), toY, toX);
                    }

                    gameState.setChecked(CheckRule.isChecked(gameState.getNextTurn(), gameState, gameState.getKingCoords(gameState.getNextTurn())));
                    if (CheckRule.isEndGame(gameState.getNextTurn(), gameState)) {
                        gameState.setEndGame(true);
                    }
                    gameState.switchTurn();
                    goodTurn = true;
                } else {
                    gameState.revertMoves();
                }
            }
        }
        endTurn();
        return goodTurn;
    }

    /**
     * Affiche le tour actuel et un check s'il y en a un
     * @param check Booléen spécifiant une mise en échec
     */
    private void displayTurn(boolean check) {
        view.displayMessage("Au tour des " + gameState.getTurn() + (check ? " / Echec" : ""));
    }

    /**
     * Gère les actions à faire lors de la fin d'un tour
     * Affiche un message d'échec et mat ou de PAT en cas de fin de partie
     */
    private void endTurn() {
        gameState.removeMovedPieces();
        if (gameState.isEndGame()) {
            if (gameState.isChecked()) {
                view.displayMessage("ECHEC ET MAT");
            } else {
                view.displayMessage("PAT");
            }
        } else {
            displayTurn(gameState.isChecked());
        }
    }

    /**
     * Retourne la pièce choisie par l'utilisateur lors de la promotion d'un pion et la place aux positions passées
     * en paramètres
     * @param toX Colonne d'arrivée
     * @param toY Ligne d'arrivée
     * @return Une pièce parmi une tour, un cavalier, un fou et une reine
     */
    private Piece promoteWithInput(int toX, int toY) {
        Piece piece;
        do {
            piece = view.askUser("Promotion du pion", "Quelle pièce choisissez-vous ?",
                    new Rook(gameState.getPiece(toY, toX).getColor()),
                    new Knight(gameState.getPiece(toY, toX).getColor()),
                    new Bishop(gameState.getPiece(toY, toX).getColor()),
                    new Queen(gameState.getPiece(toY, toX).getColor()));
        } while (piece == null);

        return piece;
    }

    /**
     * Dessine les pièces en fonction de leur position dans le gameState.
     */
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

    /**
     * Crée et remplit un tableau de pièces selon avec les positions initiales d'un début de partie
     * @return Un tableau de tableau de pièces avec les pièces placées selon les règles du jeu d'échecs
     */
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

    /* ------------ A DELETE ------------ */

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

    private Piece[][] testPATAndCheckmate() {
        Piece[][] board = new Piece[BOARD_SIZE][BOARD_SIZE];
        board[6][2] = new King(PlayerColor.WHITE);
        board[6][0] = new King(PlayerColor.BLACK);

        board[5][2] = new Queen(PlayerColor.WHITE);

        return board;
    }

    /* ------------ A DELETE ------------ */
}
