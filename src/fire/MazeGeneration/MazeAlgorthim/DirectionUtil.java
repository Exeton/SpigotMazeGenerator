package fire.MazeGeneration.MazeAlgorthim;

import org.bukkit.util.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DirectionUtil
{
    public enum Direction
    {
        North(0, new Vector(0,0,-1)),
        East(1, new Vector(1, 0, 0)),
        South(2, new Vector(0, 0, 1)),
        West(3, new Vector(-1, 0, 0)),
        None(4, new Vector(0,0,0));

        private final int value;
        private final Vector directionVector;
        Direction(int value, Vector directionVector){
            this.value = value;
            this.directionVector = directionVector;
        }

        public int getValue(){
            return  value;
        }
        public Vector getDirectionVector(){
                return directionVector;
        }
    }

    public static Direction getOpposite(Direction dir)
    {
        switch (dir)
        {
            case North:
                return Direction.South;
            case South:
                return Direction.North;
            case East:
                return Direction.West;
            case West:
                return Direction.East;
        }
        return Direction.None;
    }

    public static Point move(Direction direction, Point location)
    {
        return move(direction, location.x, location.y);
    }
    public static Point move(Direction direction, int x, int y)
    {
        Point result = new Point(x, y);
        switch (direction)
        {
            case North:
                result.y--;
                break;
            case South:
                result.y++;
                break;
            case East:
                result.x++;
                break;
            case West:
                result.x--;
                break;
        }
        return result;
    }
    public static java.util.List<Direction> getCardnialDirections(){
        List<Direction> directions = new ArrayList<>();
        directions.add(Direction.North);
        directions.add(Direction.South);
        directions.add(Direction.East);
        directions.add(Direction.West);
        return directions;
    }
}
