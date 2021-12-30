package engine.move;

public class DiagonalMove extends Move{
    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        return Math.abs(toX - fromX) == Math.abs(toY - fromY);
    }
}
