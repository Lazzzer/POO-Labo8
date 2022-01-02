package engine.move;

public class OneCellMove extends Move{
    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        return Math.abs(toX - fromX) <= 1 && Math.abs(toY - fromY) <= 1;
    }
}
