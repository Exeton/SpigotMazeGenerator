package fire.MazeGeneration.MazePopulators;

import fire.MazeGeneration.BuildingGenerator;
import fire.MazeGeneration.trapGeneration.ITrapDistributor;
import fire.MazeGeneration.trapGeneration.ITrapGenerator;
import org.bukkit.Chunk;
import org.bukkit.World;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MazeWithTrapsPopulator {

    ITrapDistributor trapDistributor;
    ITrapGenerator trapGenerator;
    List<Point> trapLocations;
    public MazeWithTrapsPopulator(BuildingGenerator buildingGenerator, ITrapDistributor trapDistributor, ITrapGenerator trapGenerator){
        this.trapDistributor = trapDistributor;
        this.trapGenerator = trapGenerator;
        trapLocations = trapDistributor.distributeTraps();
    }

    public void populate(World world, Random random, Chunk chunk) {
        List<Point> trapLocations = trapDistributor.trapsPositionsInChunk(chunk);
        for (Point trapLocation : trapLocations){
            int x = trapLocation.x * 2 + 1;
            int z = trapLocation.y * 2 + 1;
            trapGenerator.generateTrap(x, z, null);
        }
    }
}
