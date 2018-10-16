package fire.MazeGeneration.MazeAlgorthim;

import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import static fire.MazeGeneration.MazeAlgorthim.DirectionUtil.Direction;

/**
 * Generates a maze based off the recursive backtracking algorithm
 */
public class MazeGenerator
{
    Random random;
    Point currentLocation;
    MazeTile[][] tiles;

    public MazeGenerator(int xLength, int yLength, Random Random)
    {
        tiles = new MazeTile[xLength][yLength];
        random = Random;
    }

    public MazeTile[][] nextMaze()
    {
        for (int i = 0; i < tiles.length; i++)
            for (int j = 0; j < tiles[0].length; j++)
                tiles[i][j] = new MazeTile();

        int startX = random.nextInt(tiles.length); //The tile map is an array of arrays
        int startY = random.nextInt(tiles[0].length);
        generateMaze(startX, startY);
        return tiles;
    }

    private void generateMaze(int startX, int startY)
    {
        currentLocation = new Point(startX, startY);
        List<Direction> directions = DirectionUtil.getCardnialDirections();
        while (true)
        {
            MazeTile currTile = tiles[currentLocation.x][currentLocation.y];
            currTile.visited = true;

            Collections.shuffle(directions);
            Direction nextCarvableDirection = firstCarvableDirection(directions, currentLocation);

            if (nextCarvableDirection == Direction.None)//backtrack
            {
                if (currTile.originatingDirection == Direction.None)
                    return;
                currentLocation = DirectionUtil.move(currTile.originatingDirection, currentLocation);
            }
            else //Explore!!
            {
                currTile.removeWall(nextCarvableDirection);
                currentLocation = DirectionUtil.move(nextCarvableDirection, currentLocation);
                tiles[currentLocation.x][currentLocation.y].addOriginatingDirection(nextCarvableDirection);//Must be called after updating location
            }
        }
    }

    private Direction firstCarvableDirection(List<Direction> directions, Point currentLocation)
    {
        for (Direction direction : directions)
        {
            Point locationInDirection = DirectionUtil.move(direction, currentLocation);
            if (!inMaze(locationInDirection))
                continue;

            MazeTile tileInDirection = tiles[locationInDirection.x][locationInDirection.y];
            if (tileInDirection.visited)
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
        return 0 <= value && value < tiles[dimension].length;
    }

    public MazeTile[][] getMaze(){
        return tiles;
    }
}