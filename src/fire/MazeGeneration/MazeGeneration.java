package fire.MazeGeneration;

import org.bukkit.plugin.java.JavaPlugin;

public class MazeGeneration extends JavaPlugin {
    @Override
    public void onEnable(){
        getServer().getPluginManager().registerEvents(new WorldInitEventHandler(), this);
    }
    @Override
    public void onDisable(){

    }
}
