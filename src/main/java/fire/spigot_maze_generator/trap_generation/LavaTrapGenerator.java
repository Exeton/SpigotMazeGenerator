package fire.spigot_maze_generator.trap_generation;

import fire.spigot_maze_generator.BuildingGenerator;
import fire.spigot_maze_generator.maze_algorthim.DirectionUtil;
import fire.spigot_maze_generator.maze_algorthim.MazeTile;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.util.Vector;

public class LavaTrapGenerator implements  ITrapGenerator{
    private BuildingGenerator buildingGenerator;
    public LavaTrapGenerator(BuildingGenerator buildingGenerator){
        this.buildingGenerator = buildingGenerator;
    }

    @Override
    public void generateTrap(int x, int z, MazeTile tile) {

        Vector lavaLoc = new Vector(x, 0, z).add(buildingGenerator.buildingHeightAsVector());
        DirectionUtil.Direction direction = DirectionUtil.Direction.North;

        if (!tile.hasWall(DirectionUtil.Direction.North))
            direction = DirectionUtil.Direction.South;
        else if (!tile.hasWall(DirectionUtil.Direction.West))
            direction = DirectionUtil.Direction.East;

        //Translate lava pos to starting position. (x and z parameters are the center of the block)
        for (int i = 0; i < 2; i++)
            lavaLoc.subtract(direction.getDirectionVector());

        for (int i = 0; i < 3; i++){
            buildingGenerator.setBlockType(lavaLoc, Material.LAVA);
            buildingGenerator.setBlockType(lavaLoc.clone().subtract(new Vector(0, 1, 0)), Material.GLASS);
            lavaLoc = lavaLoc.add(direction.getDirectionVector());
        }

    }
}
