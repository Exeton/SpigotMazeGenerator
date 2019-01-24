package fire.spigot_maze_generator.commands;

import fire.spigot_maze_generator.BuildingGenerator;
import fire.spigot_maze_generator.MazeBlockBuilder;
import fire.spigot_maze_generator.block_placing.FAWEBlockPlacer;
import fire.spigot_maze_generator.maze_algorthim.MazeGenerator;
import fire.spigot_maze_generator.trap_generation.LavaTrapGenerator;
import fire.spigot_maze_generator.trap_generation.MazeTrapPlacer;
import fire.spigot_maze_generator.trap_generation.MultiTrapGenerator;
import fire.spigot_maze_generator.trap_generation.TnTTrapGenerator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

import static fire.spigot_maze_generator.MazeBlockBuilder.blocksToCells;

public class GenerateMazeCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player))
            return false;
        Player player = (Player)commandSender;


        int mazeLenX = 65;
        int mazeLenZ = 65;

        MazeGenerator mazeGenerator = new MazeGenerator(blocksToCells(mazeLenX), blocksToCells(mazeLenZ), new Random());
        FAWEBlockPlacer worldEditPlacer = new FAWEBlockPlacer();

        Location loc = player.getLocation();

        //Adjust amount of blocks
        worldEditPlacer.resetSession(player.getWorld(), mazeLenX * mazeLenZ * 10, loc.getBlockX() - 1, loc.getBlockY() - 5, loc.getBlockZ() - 1);

        BuildingGenerator buildingGenerator =  new BuildingGenerator(worldEditPlacer,4, 3, Material.STAINED_CLAY, Material.STONE);

        MazeBlockBuilder mazeMapWriter = new MazeBlockBuilder(buildingGenerator, mazeGenerator);
        mazeMapWriter.fillStructureMap();

        MultiTrapGenerator multiTrapGenerator = new MultiTrapGenerator(new LavaTrapGenerator(buildingGenerator), new TnTTrapGenerator(buildingGenerator));

        MazeTrapPlacer mazeTrapPlacer = new MazeTrapPlacer(multiTrapGenerator, mazeLenX, mazeLenZ);
        mazeTrapPlacer.addTraps(mazeGenerator.getMaze());

        worldEditPlacer.applyChanges();

        player.sendMessage("Maze created");
        return true;
    }
}
