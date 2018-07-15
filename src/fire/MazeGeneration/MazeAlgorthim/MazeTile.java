package fire.MazeGeneration.MazeAlgorthim;

import static fire.MazeGeneration.MazeAlgorthim.DirectionUtil.Direction;

public class MazeTile
{
    private boolean[] walls = new boolean[4];//True if there is a wall
    boolean visited;
    public Direction originatingDirection = Direction.None;

    public MazeTile()
    {
        for (int i = 0; i < walls.length; i++)
            walls[i] = true;
        visited = false;
    }

    public void removeWall(Direction direction)
    {
        walls[direction.getValue()] = false;
    }
    public void addOriginatingDirection(Direction direction)
    {
        Direction originatingDirection = DirectionUtil.getOpposite(direction);
        if (originatingDirection != Direction.None)
        {
            walls[originatingDirection.getValue()] = false;
            this.originatingDirection = originatingDirection;
        }
    }

    public boolean hasWall(Direction direction)
    {
        return walls[direction.getValue()];
    }
}
