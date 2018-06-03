package fire.MazeGeneration.trapGeneration;

import fire.MazeGeneration.BuildingGenerator;
import fire.MazeGeneration.MazeAlgorthim.DirectionUtil;
import fire.MazeGeneration.MazeAlgorthim.MazeTile;
import org.bukkit.Material;
import org.bukkit.util.Vector;

public class LavaTrapGenerator implements  ITrapGenerator{
    private BuildingGenerator buildingGenerator;
    public LavaTrapGenerator(BuildingGenerator buildingGenerator){
        this.buildingGenerator = buildingGenerator;
    }

    @Override
    public void generateTrap(int x, int z, MazeTile tile) {

        Vector floorLoc = new Vector(x, 0, z).add(buildingGenerator.buildingHeightAsVector());
        Vector lavaLoc = new Vector(x, -1, z).add(buildingGenerator.buildingHeightAsVector());
        DirectionUtil.Direction direction = DirectionUtil.Direction.North;

        //Translate floor pos and lava to starting position. (x and z parameters are the center of the block)
        if (!tile.hasWall(DirectionUtil.Direction.North))
            direction = DirectionUtil.Direction.South;
        else if (!tile.hasWall(DirectionUtil.Direction.West))
            direction = DirectionUtil.Direction.East;

        //Translate floor pos and lava to starting position. (x and z parameters are the center of the block)
        for (int i = 0; i < 2; i++){
            floorLoc.subtract(direction.getDirectionVector());
            lavaLoc.subtract(direction.getDirectionVector());
        }

        for (int i = 0; i < 3; i++){
            buildingGenerator.setBlockType(floorLoc, Material.AIR);
            buildingGenerator.setBlockType(lavaLoc, Material.LAVA);
            floorLoc = floorLoc.add(direction.getDirectionVector());
            lavaLoc = lavaLoc.add(direction.getDirectionVector());
        }
    }
}
