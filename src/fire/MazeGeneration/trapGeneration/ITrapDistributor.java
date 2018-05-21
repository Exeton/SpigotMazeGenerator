package fire.MazeGeneration.trapGeneration;

import org.bukkit.Chunk;

import  java.awt.Point;
import java.util.List;

public interface ITrapDistributor {
    List<Point> distributeTraps();
    List<Point> trapsPositionsInChunk(Chunk chunk);//Returns traps in the chunk, in maze coords relative to the chunk
}
