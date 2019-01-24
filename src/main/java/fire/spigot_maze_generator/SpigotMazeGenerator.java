package fire.spigot_maze_generator;

import fire.spigot_maze_generator.block_placing.FAWEBlockPlacer;
import fire.spigot_maze_generator.commands.GenerateMazeCommand;
import fire.spigot_maze_generator.maze_algorthim.MazeGenerator;
import fire.spigot_maze_generator.trap_generation.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class SpigotMazeGenerator extends JavaPlugin {

    public static int BlocksSquaredPerTrap;
    public static boolean placeTraps;

    @Override
    public void onEnable(){

        saveDefaultConfig();
        Configuration config = getConfig();

        BlocksSquaredPerTrap = config.getInt("BlocksPerTrap");
        Material wallMaterial = getMaterialFromName(config.getString("WallMaterial"));
        Material floorMaterial = getMaterialFromName(config.getString("FloorMaterial"));
        placeTraps = config.getBoolean("GenerateTraps");

        MazeGenerator mazeGenerator = new MazeGenerator(new Random());
        FAWEBlockPlacer worldEditPlacer = new FAWEBlockPlacer();
        BuildingGenerator buildingGenerator =  new BuildingGenerator(worldEditPlacer,4, 3, wallMaterial, floorMaterial);
        MazeBlockBuilder mazeMapWriter = new MazeBlockBuilder(buildingGenerator, mazeGenerator);

        MazeTrapPlacer mazeTrapPlacer = null;
        if (placeTraps){

            List<ITrapGenerator> trapGenerators = new LinkedList<>();
            if (config.getBoolean("GenerateLavaTrap"))
                trapGenerators.add(new LavaTrapGenerator(buildingGenerator));
            if (config.getBoolean("GenerateTnTTrap"))
                trapGenerators.add(new TnTTrapGenerator(buildingGenerator));

            mazeTrapPlacer = new MazeTrapPlacer(new MultiTrapGenerator(trapGenerators.toArray(new ITrapGenerator[trapGenerators.size()])));
        }

        this.getCommand("mazeGen").setExecutor(new GenerateMazeCommand(worldEditPlacer, mazeTrapPlacer, mazeGenerator, mazeMapWriter));
    }


    @Override
    public void onDisable(){

    }

    public Material getMaterialFromName(String name){
        Material material = Material.matchMaterial(name);
        if (material == null)
            material = Bukkit.getUnsafe().getMaterialFromInternalName(name);

        return material;
    }
}
