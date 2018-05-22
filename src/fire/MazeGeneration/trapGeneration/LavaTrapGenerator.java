package fire.MazeGeneration.trapGeneration;

import fire.MazeGeneration.BuildingGenerator;
import fire.MazeGeneration.MazeAlgorthim.DirectionUtil;
import fire.MazeGeneration.MazeAlgorthim.MazeTile;
import org.bukkit.Material;
import org.bukkit.util.Vector;

public class LavaTrapGenerator implements  ITrapGenerator{
    BuildingGenerator buildingGenerator;
    public LavaTrapGenerator(BuildingGenerator buildingGenerator){
        this.buildingGenerator = buildingGenerator;
    }

    @Override
    public void generateTrap(int x, int z, MazeTile tile) {

        Vector floorLoc = new Vector(x, 0, z).add(buildingGenerator.buildingHeightAsVector());
        Vector lavaLoc = new Vector(x, -1, z).add(buildingGenerator.buildingHeightAsVector());
        Vector offsetVector = new Vector (0,0,0);
        if (!tile.hasWall(DirectionUtil.Direction.North)){
            floorLoc = floorLoc.add(DirectionUtil.Direction.North.getDirectionVector());
            floorLoc = floorLoc.add(DirectionUtil.Direction.North.getDirectionVector());
            lavaLoc = lavaLoc.add(DirectionUtil.Direction.North.getDirectionVector());
            lavaLoc = lavaLoc.add(DirectionUtil.Direction.North.getDirectionVector());
            offsetVector = new Vector(0, 0, 1);
        }
        else if (!tile.hasWall(DirectionUtil.Direction.West)){
            floorLoc = floorLoc.add(DirectionUtil.Direction.West.getDirectionVector());
            floorLoc = floorLoc.add(DirectionUtil.Direction.West.getDirectionVector());
            lavaLoc = lavaLoc.add(DirectionUtil.Direction.West.getDirectionVector());
            lavaLoc = lavaLoc.add(DirectionUtil.Direction.West.getDirectionVector());
            offsetVector = new Vector(1, 0, 0);
        }

        for (int i = 0; i < 3; i++){
            buildingGenerator.setBlockType(floorLoc, Material.AIR);
            buildingGenerator.setBlockType(lavaLoc, Material.LAVA);
            floorLoc = floorLoc.add(offsetVector);
            lavaLoc = lavaLoc.add(offsetVector);
        }
    }
}
