package fire.MazeGeneration.trapGeneration;

import fire.MazeGeneration.BuildingGenerator;
import fire.MazeGeneration.MazeAlgorthim.MazeTile;
import org.bukkit.Material;
import org.bukkit.util.Vector;

public class TnTTrapGenerator implements  ITrapGenerator{
    private BuildingGenerator buildingGenerator;
    public TnTTrapGenerator(BuildingGenerator buildingGenerator){
        this.buildingGenerator = buildingGenerator;
    }

    @Override
    public void generateTrap(int x, int z, MazeTile tile) {
        Vector pressurePlateLocation = new Vector(x, 1, z).add(buildingGenerator.buildingHeightAsVector());
        buildingGenerator.setBlockType(pressurePlateLocation, Material.STONE_PLATE);

        int clusterSize = 2;//Using sizes > 2 will cause a large chain reaction of tnt when activated

        Vector startPos = new Vector(x - 1, -clusterSize, z - 1).add(buildingGenerator.buildingHeightAsVector());
        Vector tntClusterSize = new Vector(clusterSize, clusterSize, clusterSize);
        buildingGenerator.fillRoom(startPos,tntClusterSize , Material.TNT);
    }
}
