package fire.spigot_maze_generator;

import org.bukkit.plugin.java.JavaPlugin;

public class SpigotMazeGenerator extends JavaPlugin {
    @Override
    public void onEnable(){
        getServer().getPluginManager().registerEvents(new WorldInitEventHandler(), this);
    }
    @Override
    public void onDisable(){

    }
}
