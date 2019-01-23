package fire.spigot_maze_generator;


import fire.spigot_maze_generator.maze_algorthim.MazeGenerator;
import fire.spigot_maze_generator.maze_populators.MappedStructurePopulator;
import fire.spigot_maze_generator.block_placing.IBlockPlacer;
import fire.spigot_maze_generator.block_placing.MappingBlockPlacer;
import fire.spigot_maze_generator.trap_generation.*;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.util.Vector;

import java.util.Random;

import static fire.spigot_maze_generator.MazeBlockBuilder.blocksToCells;

public class WorldInitEventHandler implements Listener {
    @EventHandler
    public void onInit(WorldInitEvent event)
    {
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
    }
}
