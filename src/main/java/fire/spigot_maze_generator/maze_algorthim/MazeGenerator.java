package fire.spigot_maze_generator.maze_algorthim;

import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import static fire.spigot_maze_generator.maze_algorthim.DirectionUtil.Direction;

/**
 * Generates a maze based off the recursive backtracking algorithm
 */
public class MazeGenerator
{
    Random random;
    Point currentLocation;
    MazeTile[][] tiles;
    List<Direction> directions = DirectionUtil.getCardnialDirections();

    public MazeGenerator(Random Random)
    {
        random = Random;
    }

    public MazeTile[][] nextMaze(int xLength, int yLength)
    {
        tiles = new MazeTile[xLength][yLength];
        for (int i = 0; i < tiles.length; i++)
            for (int j = 0; j < tiles[0].length; j++)
                tiles[i][j] = new MazeTile();

        int startX = random.nextInt(tiles.length);
        int startY = random.nextInt(tiles[0].length);
        generateMaze(startX, startY);
        return tiles;
    }

    private MazeTile getCurrentTile(){
        return tiles[currentLocation.x][currentLocation.y];
    }

    private void generateMaze(int startX, int startY){
        currentLocation = new Point(startX, startY);
        while (true){

            while (canStep())
                step();

            backtrack();
            if (!canStep() && getCurrentTile().originatingDirection == Direction.None) //Check finished
                return;
        }
    }

    private boolean canStep(){
        return firstCarvableDirection(directions, currentLocation) != Direction.None;
    }

    private void step(){
        Direction direction = nextDirection();
        getCurrentTile().removeWall(direction);
        currentLocation = DirectionUtil.move(direction, currentLocation);
        getCurrentTile().addOriginatingDirection(direction);//Must be called after updating location
    }

    private void backtrack(){
        currentLocation = DirectionUtil.move(getCurrentTile().originatingDirection, currentLocation);
    }

    private Direction nextDirection(){
        Collections.shuffle(directions);
        return firstCarvableDirection(directions, currentLocation);
    }

    private Direction firstCarvableDirection(List<Direction> directions, Point currentLocation)
    {
        for (Direction direction : directions)
        {
            Point locationInDirection = DirectionUtil.move(direction, currentLocation);
            if (!inMaze(locationInDirection))
                continue;

            MazeTile tileInDirection = tiles[locationInDirection.x][locationInDirection.y];
            if (tileInDirection.hasMissingWall)
                continue;

            return direction;
        }
        return Direction.None;
    }
    private boolean inMaze(Point location)
    {
        return inMazeDimension(location.x, 0) && inMazeDimension(location.y, 1);
    }

    private boolean inMazeDimension(int value, int dimension)
    {
        int size = 0;
        if (dimension == 0)
            size = tiles.length;
        else if (dimension == 1)
            size = tiles[0].length;

        return 0 <= value && value < size;
    }

    public MazeTile[][] getMaze(){
        return tiles;
    }
}