package fire.spigot_maze_generator.commands;

import fire.spigot_maze_generator.BuildingGenerator;
import fire.spigot_maze_generator.MazeBlockBuilder;
import fire.spigot_maze_generator.SpigotMazeGenerator;
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


import static fire.spigot_maze_generator.MazeBlockBuilder.blocksToCells;

public class GenerateMazeCommand implements CommandExecutor {

    FAWEBlockPlacer worldEditPlacer;
    MazeTrapPlacer mazeTrapPlacer;
    MazeGenerator mazeGenerator;
    MazeBlockBuilder mazeBuilder;


    public GenerateMazeCommand(FAWEBlockPlacer worldEditPlacer, MazeTrapPlacer mazeTrapPlacer, MazeGenerator mazeGenerator, MazeBlockBuilder mazeBuilder){
        this.worldEditPlacer = worldEditPlacer;
        this.mazeTrapPlacer = mazeTrapPlacer;
        this.mazeGenerator = mazeGenerator;
        this.mazeBuilder = mazeBuilder;
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player))
            return false;
        Player player = (Player)commandSender;


        if (strings.length < 2)
            return false;

        int mazeLenX;
        int mazeLenZ;

        try {
            mazeLenX = Integer.parseInt(strings[0]);
            mazeLenZ = Integer.parseInt(strings[1]);
        }
        catch(Exception e){
            return false;
        }
        Location loc = player.getLocation();

        //Adjust amount of blocks
        worldEditPlacer.resetSession(player.getWorld(), mazeLenX * mazeLenZ * 6, loc.getBlockX() - 1, loc.getBlockY() - 5, loc.getBlockZ() - 1);
        mazeBuilder.fillStructureMap(blocksToCells(mazeLenX), blocksToCells(mazeLenZ));

        if (SpigotMazeGenerator.placeTraps)
            mazeTrapPlacer.addTraps(mazeGenerator.getMaze(), blocksToCells(mazeLenX), blocksToCells(mazeLenZ));

        worldEditPlacer.applyChanges();

        player.sendMessage("Maze created");
        return true;
    }
}
