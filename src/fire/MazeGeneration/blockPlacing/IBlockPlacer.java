package fire.MazeGeneration.blockPlacing;

import org.bukkit.Material;
import org.bukkit.util.Vector;

public interface IBlockPlacer {
    void setBlock(Vector location, Material blockType);
}
