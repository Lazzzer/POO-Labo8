package engine.piece;

import chess.ChessView.UserChoice;
import chess.PieceType;
import chess.PlayerColor;
import engine.GameState;

/**
 * Classe représentant une pièce
 * @author Alexandre Jaquier
 * @author Lazar Pavicevic
 */
public abstract class Piece implements UserChoice {
    protected PieceType type;
    protected PlayerColor color;

    /**
     * Constructeur de base
     * @param type Type de pièce
     * @param color Couleur de la pièce
     */
    protected Piece(PieceType type, PlayerColor color) {
        this.type = type;
        this.color = color;
    }

    /**
     * Getter sur color
     * @return La couleur de la pièce
     */
    public PlayerColor getColor(){
        return color;
    }

    /**
     * Getter sur type
     * @return Le type de la pièce
     */
    public PieceType getPieceType(){
        return type;
    }

    /**
     * Retourne une string avec le nom de la classe
     * @return Le nom de la classe
     */
    @Override
    public String textValue() {
        return getClass().getSimpleName();
    }

    /**
     * Retourne une string avec le nom de la classe
     * @return Le nom de la classe
     */
    public String toString() {
        return textValue();
    }

    /**
     * Contrôle si le déplacement de la pièce vers une case donnée est légal ou non
     * @param gameState État du jeu
     * @param fromX Colonne de départ
     * @param fromY Ligne de départ
     * @param toX Colonne d'arrivée
     * @param toY Ligne d'arrivée
     * @return Vrai si le mouvement est légal
     */
    public abstract boolean move(GameState gameState, int fromX, int fromY, int toX, int toY);

    /**
     * Clône la pièce
     * @return La pièce clônée
     */
    public abstract Piece clone();
}