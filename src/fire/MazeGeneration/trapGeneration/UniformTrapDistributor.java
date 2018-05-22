package fire.MazeGeneration.trapGeneration;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class UniformTrapDistributor implements ITrapDistributor{
    int sizeX;
    int sizeZ;
    int uniformityMultiplier = 1;
    List<Point> trapLocations = new LinkedList<Point>();
    Random r = new Random();
    public UniformTrapDistributor(int SizeX, int SizeZ){
        sizeX = SizeX;
        sizeZ = SizeZ;
        Bukkit.getLogger().info("x: " + sizeX + " z:" + sizeZ);
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

    //Todo : prevent stack overflow exceptions.
    public void addTrap()
    {
        Point potentialLocation = randomLocation(sizeX, sizeZ);
        int distanceSqFromNearestPoint = distanceSqFromNearestTrap(potentialLocation);

        if (distanceSqFromNearestPoint == 0)
            distanceSqFromNearestPoint = 1;

        int pOfSwitchingLocation = uniformityMultiplier * sizeX * sizeZ / distanceSqFromNearestPoint;

        if (r.nextInt(101) < pOfSwitchingLocation)
            addTrap();//Recalling this function means the trap will be placed in a diff location instead of the one selected by the function
        else
            trapLocations.add(potentialLocation);
    }
    public Point randomLocation(int maxX, int maxY)
    {
        return new Point(r.nextInt(maxX), r.nextInt(maxY));
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
