package fire.spigot_maze_generator.block_placing;

import org.bukkit.Material;
import org.bukkit.util.Vector;

public interface IBlockPlacer {
    void setBlock(Vector location, Material blockType);
}
