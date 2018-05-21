package fire.MazeGeneration;


import fire.MazeGeneration.MazePopulators.MazeStructurePopulator;
import fire.MazeGeneration.MazePopulators.MazeWithTrapsPopulator;
import fire.MazeGeneration.blockPlacing.ChunkBlockPlacer;
import fire.MazeGeneration.blockPlacing.IBlockPlacer;
import fire.MazeGeneration.trapGeneration.ITrapDistributor;
import fire.MazeGeneration.trapGeneration.TnTTrapGenerator;
import fire.MazeGeneration.trapGeneration.UniformTrapDistributor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;

public class WorldInitEventHandler implements Listener {
    @EventHandler
    public void onInit(WorldInitEvent event)
    {
        IBlockPlacer blockPlacer = new ChunkBlockPlacer();
        BuildingGenerator buildingGenerator =  new BuildingGenerator(blockPlacer,100, 3, Material.WOOD, Material.STONE);
        ITrapDistributor trapDistributor = new UniformTrapDistributor(16, 16);//Change to use mazeStructurePopulatos stuff
        event.getWorld().getPopulators().add(new MazeWithTrapsPopulator(buildingGenerator, trapDistributor , new TnTTrapGenerator(buildingGenerator)));
    }
}
