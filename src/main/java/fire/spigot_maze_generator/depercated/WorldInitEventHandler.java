package fire.spigot_maze_generator.depercated;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;

public class WorldInitEventHandler implements Listener {
    @EventHandler
    public void onInit(WorldInitEvent event)
    {
        /*
        //Todo fix error with maze generating to be 1 size even if mazeLenX and Z are diff
        int mazeLenX = 65;
        int mazeLenZ = 65;

        MazeGenerator mazeGenerator = new MazeGenerator(blocksToCells(mazeLenX), blocksToCells(mazeLenZ), new Random());
        StructureMapper structureMapper = new StructureMapper(new Vector(0,0,0), new Vector(100,255,100));
        IBlockPlacer blockPlacer = new MappingBlockPlacer(structureMapper);
        BuildingGenerator buildingGenerator =  new BuildingGenerator(blockPlacer,4, 3, Material.STAINED_CLAY, Material.STONE);

        MazeBlockBuilder mazeMapWriter = new MazeBlockBuilder(buildingGenerator, mazeGenerator);
        mazeMapWriter.fillStructureMap();
        event.getWorld().getPopulators().add(new MappedStructurePopulator(structureMapper));

        MultiTrapGenerator multiTrapGenerator = new MultiTrapGenerator(new LavaTrapGenerator(buildingGenerator), new TnTTrapGenerator(buildingGenerator));

        MazeTrapPlacer mazeTrapPlacer = new MazeTrapPlacer(multiTrapGenerator, mazeLenX, mazeLenZ);
        mazeTrapPlacer.addTraps(mazeGenerator.getMaze());
        */
    }
}
