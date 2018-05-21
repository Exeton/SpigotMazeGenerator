package fire.MazeGeneration;


import fire.MazeGeneration.MazePopulators.MappedStructurePopulator;
import fire.MazeGeneration.blockPlacing.IBlockPlacer;
import fire.MazeGeneration.blockPlacing.MappingBlockPlacer;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.util.Vector;

public class WorldInitEventHandler implements Listener {
    @EventHandler
    public void onInit(WorldInitEvent event)
    {
        //ITrapDistributor trapDistributor = new UniformTrapDistributor(16, 16);//Change to use mazeStructurePopulatos stuff

        StructureMapper structureMapper = new StructureMapper(new Vector(0,0,0), new Vector(100,255,100));
        IBlockPlacer blockPlacer = new MappingBlockPlacer(structureMapper);
        BuildingGenerator buildingGenerator =  new BuildingGenerator(blockPlacer,100, 3, Material.WOOD, Material.STONE);

        MazeBlockBuilder mazeMapWriter = new MazeBlockBuilder(buildingGenerator);
        mazeMapWriter.fillStructureMap();
        event.getWorld().getPopulators().add(new MappedStructurePopulator(structureMapper));
    }
}
