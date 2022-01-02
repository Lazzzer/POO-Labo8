package engine.move;

public class LMove extends Move {
    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        return (toX-fromX) * (toX-fromX) + (toY-fromY) * (toY-fromY) == 5;
    }
}
