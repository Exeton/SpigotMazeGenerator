package fire.MazeGeneration.trapGeneration;

import fire.MazeGeneration.BuildingGenerator;
import fire.MazeGeneration.MazeAlgorthim.MazeTile;
import org.bukkit.Material;
import org.bukkit.util.Vector;

public class TnTTrapGenerator implements  ITrapGenerator{
    BuildingGenerator buildingGenerator;
    public TnTTrapGenerator(BuildingGenerator buildingGenerator){
        this.buildingGenerator = buildingGenerator;
    }

    @Override
    public void generateTrap(int x, int z, MazeTile tile) {
        Vector presurePlateLocation = new Vector(x, 1, z).add(buildingGenerator.buildingHeightAsVector());
        Vector tntLocation = new Vector(x, -1, z).add(buildingGenerator.buildingHeightAsVector());
        Vector tntLocation2 = new Vector(x, -2, z).add(buildingGenerator.buildingHeightAsVector());
        Vector blockUnderTnt = new Vector(x, -3, z).add(buildingGenerator.buildingHeightAsVector());

        buildingGenerator.setBlockType(presurePlateLocation, Material.STONE_PLATE);
        buildingGenerator.setBlockType(tntLocation, Material.TNT);
        buildingGenerator.setBlockType(tntLocation2, Material.TNT);
        buildingGenerator.setBlockType(blockUnderTnt, Material.STONE);
    }
}
