package fire.MazeGeneration.trapGeneration;

import org.bukkit.Chunk;

import  java.awt.Point;
import java.util.List;

public interface ITrapDistributor {
    List<Point> distributeTraps();
}
