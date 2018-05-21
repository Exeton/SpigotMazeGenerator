package fire.MazeGeneration.MazeAlgorthim;

import org.bukkit.Bukkit;

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
    Random random = new Random();
    Point currentLocation;
    MazeTile[][] tiles;

    public MazeGenerator(int xLength, int yLength)//Outermost tiles reserved for maze border.
    {
        tiles = new MazeTile[xLength][yLength];
    }

    public MazeTile[][] generateMaze()
    {
        for (int i = 0; i < tiles[0].length; i++)
        {
            for (int j = 0; j < tiles[1].length; j++)
                tiles[i][j] = new MazeTile();
        }
        int startX = random.nextInt(tiles[0].length);
        int startY = random.nextInt(tiles[1].length);
        carvePassages(startX, startY);

        addMazeBorder(tiles);
        return tiles;
    }

    //todo Clean up
    private void addMazeBorder(MazeTile[][] maze){

        for (int x = 0; x < maze[0].length; x++){
            maze[x][maze[1].length - 1] = new MazeTile();
        }
        for (int y = 0; y < maze[1].length; y++){
            MazeTile newTile = new MazeTile();
            maze[maze[0].length - 1][y] = newTile;
        }
    }

    private void carvePassages(int startX, int startY)
    {
        tiles[startX][startY].visited = true;
        currentLocation = new Point(startX, startY);
        List<Direction> directions = DirectionUtil.getCardnialDirections();
        while (true)
        {
            Collections.shuffle(directions);
            MazeTile currTile = tiles[currentLocation.x][currentLocation.y];
            Direction nextDirection = firstCarvableDirection(directions, currentLocation);
            if (nextDirection == Direction.None)//backtrack
            {
                if (currTile.originatingDirection == Direction.None)
                    return;
                currTile.visited = true;//Sometimes a tile cannot carve a new tile. It must be marked visited too
                currentLocation = DirectionUtil.move(currTile.originatingDirection, currentLocation);
            }
            else //Explore!!
            {
                currTile.carveTile(nextDirection);
                currentLocation = DirectionUtil.move(nextDirection, currentLocation);
                tiles[currentLocation.x][currentLocation.y].addOriginatingDirection(nextDirection);//Must be called after updating location
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
            if (tileInDirection.visited == true)
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
        return 0 <= value && value < tiles[dimension].length - 1;//Leave room for maze border
    }

    public MazeTile[][] getMazeTiles(int startX, int startZ, int tilesX, int tilesZ)
    {
        if (!inMaze(new Point(startX, startZ)))
            return  new MazeTile[0][0];
        if (!inMazeDimension(startX + tilesX, 0)){
            tilesX =  tiles[0].length - startX;
        }
        if (!inMazeDimension(startZ + tilesZ, 0)) {
            tilesZ = tiles[0].length - startZ;
        }


        MazeTile[][] section = new MazeTile[tilesX][tilesZ];
        for (int x = 0; x < tilesX; x++){
            for (int z = 0; z <tilesZ; z++){
                section[x][z] = tiles[startX + x][startZ + z];
            }
        }
        return section;
    }
}