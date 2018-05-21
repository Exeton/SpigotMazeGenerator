package fire.MazeGeneration.trapGeneration;

import org.bukkit.Chunk;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class UniformTrapDistributor implements  ITrapDistributor{
    int sizeX;
    int sizeZ;
    int uniformityMultiplier = 1;
    List<Point> trapLocations = new LinkedList<Point>();
    Random r = new Random();
    public UniformTrapDistributor(int SizeX, int SizeZ){
        sizeX = SizeX;
        sizeZ = SizeZ;
    }

    @Override
    public List<Point> distributeTraps() {
        trapLocations = new LinkedList<Point>();
        for (int traps = 0; traps < 10; traps++)
        {
            addTrap();
        }
        return trapLocations;
    }

    public List<Point> trapsPositionsInChunk(Chunk chunk)
    {
        List<Point> trapPositionsInChunk = new LinkedList<Point>();
        for (Point trapLocation : trapLocations){
            if (trapInChunkDimension(chunk.getX(), trapLocation.x) &&trapInChunkDimension(chunk.getZ(), trapLocation.y)){
                int newX = trapLocation.x - 8 * chunk.getX();
                int newZ = trapLocation.y - 8 * chunk.getZ();
                trapPositionsInChunk.add(new Point(newX, newZ));
            }
        }
        return trapPositionsInChunk;
    }
    private boolean trapInChunkDimension(int chunkCoord, int trapCoordInMaze){
        int trapCoordInWorld = 2 * trapCoordInMaze + 1;

        int chunkStart = 16 * chunkCoord;
        int chunkEnd = 16 * chunkStart + 15;
        return chunkStart < trapCoordInWorld && trapCoordInWorld < chunkEnd;
    }

    //Todo : prevent stack overflow exceptions.
    public void addTrap()
    {
        Point potentialLocation = randomLocation(sizeX, sizeZ);
        int distanceSqFromNearestPoint = distanceSqFromNearestTrap(potentialLocation);

        if (distanceSqFromNearestPoint == 0)
            distanceSqFromNearestPoint = 1;

        int pOfSwitchingLocation = uniformityMultiplier * sizeX * sizeZ / distanceSqFromNearestPoint;

        if (r.nextInt(101) < pOfSwitchingLocation)
            addTrap();//Switch locations
        else
            trapLocations.add(potentialLocation);
    }
    public Point randomLocation(int maxX, int maxY)
    {
        return new Point(r.nextInt(sizeX + 1), r.nextInt(sizeZ + 1));
    }
    public int distanceSqFromNearestTrap(Point point)
    {
        int smallestDistanceSquared = 10000;
        for (Point location : trapLocations)
        {
            int distanceSqFrom = (int)location.distanceSq(point);
            if (distanceSqFrom < smallestDistanceSquared)
                smallestDistanceSquared = distanceSqFrom;
        }
        return (int)Math.sqrt(smallestDistanceSquared);
    }
}
