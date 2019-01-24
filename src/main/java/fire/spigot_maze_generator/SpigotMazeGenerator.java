package fire.spigot_maze_generator;

import fire.spigot_maze_generator.commands.GenerateMazeCommand;
import org.bukkit.plugin.java.JavaPlugin;


public class SpigotMazeGenerator extends JavaPlugin {
    @Override
    public void onEnable(){

        this.getCommand("mazeGen").setExecutor(new GenerateMazeCommand());
        getServer().getPluginManager().registerEvents(new WorldInitEventHandler(), this);
    }
    @Override
    public void onDisable(){

    }
}
