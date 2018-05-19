package fire.MazeGeneration.MazePopulators;

import fire.MazeGeneration.trapGeneration.ITrapDistributor;
import fire.MazeGeneration.trapGeneration.ITrapGenerator;
import org.bukkit.Chunk;
import org.bukkit.World;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class MazeWithTrapsPopulator extends MazeStructurePopulator{

    ITrapDistributor trapDistributor;
    ITrapGenerator trapGenerator;
    List<Point> trapLocations;
    public MazeWithTrapsPopulator(ITrapDistributor trapDistributor, ITrapGenerator trapGenerator){
        super();
        this.trapDistributor = trapDistributor;
        this.trapGenerator = trapGenerator;
        trapLocations = trapDistributor.distributeTraps();
    }

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        super.populate(world, random, chunk);
        //Todo trap distributor coordinates must be converted to chunk coordinates.
    }
}
