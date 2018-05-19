package fire.MazeGeneration;


import fire.MazeGeneration.MazePopulators.MazeStructurePopulator;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;

public class WorldInitEventHandler implements Listener {
    @EventHandler
    public void onInit(WorldInitEvent event)
    {
        event.getWorld().getPopulators().add(new MazeStructurePopulator());
    }
}
