package fire.MazeGeneration;

import org.bukkit.Bukkit;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class MazeGeneration extends JavaPlugin {
    @Override
    public void onEnable(){
        Bukkit.getLogger().info("On Enable Called");
        getServer().getPluginManager().registerEvents(new WorldInitEventHandler(), this);
    }
    @Override
    public void onDisable(){

    }
}
