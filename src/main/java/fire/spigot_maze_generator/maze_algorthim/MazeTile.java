package fire.spigot_maze_generator.maze_algorthim;

import java.awt.*;

import static fire.spigot_maze_generator.maze_algorthim.DirectionUtil.Direction;

public class MazeTile
{
    private boolean[] walls = new boolean[4];//True if there is a wall
    public boolean hasMissingWall;
    public Direction originatingDirection = Direction.None;

    public MazeTile()
    {
        for (int i = 0; i < walls.length; i++)
            walls[i] = true;
        hasMissingWall = false;
    }

    public void removeWall(Direction direction)
    {
        walls[direction.getValue()] = false;
        hasMissingWall = true;
    }
    public void addOriginatingDirection(Direction direction)
    {
        Direction originatingDirection = DirectionUtil.getOpposite(direction);
        if (originatingDirection != Direction.None)
        {
            removeWall(originatingDirection);
            this.originatingDirection = originatingDirection;
        }
    }

    public boolean hasWall(Direction direction)
    {
        return walls[direction.getValue()];
    }

    public static boolean canTravel(MazeTile[][] maze, Point mazeTileLoc, Direction direction){
        //Incomplete
        MazeTile startingTile = maze[mazeTileLoc.x][mazeTileLoc.y];
        if (!startingTile.hasWall(direction))
            return true;

        return false;
    }
}
