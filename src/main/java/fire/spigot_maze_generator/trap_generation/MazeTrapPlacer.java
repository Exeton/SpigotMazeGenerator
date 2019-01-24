package fire.spigot_maze_generator.trap_generation;

import fire.spigot_maze_generator.maze_algorthim.MazeTile;

import java.awt.*;
import java.util.List;

import static fire.spigot_maze_generator.MazeBlockBuilder.blocksToCells;

public class MazeTrapPlacer {
    ITrapDistributor trapDistributor;
    ITrapGenerator trapGenerator;
    public MazeTrapPlacer(ITrapGenerator trapGenerator){
        //trapGenerator.generateTrap(x,z) should be called with the center of the maze cell (Not where a wall is)
        //To do this we call blocksToCells, then scale the trap coords back up when placing the trap
        this.trapDistributor = new UniformTrapDistributor();
        this.trapGenerator = trapGenerator;
    }
    public void addTraps(MazeTile[][] mazeTiles, int mazeTilesX, int mazeTilesZ){
        List<Point> traps = trapDistributor.distributeTraps(mazeTilesX, mazeTilesZ);
        for (Point trapLoc : traps){
            MazeTile trapTile = mazeTiles[trapLoc.x][trapLoc.y];
            trapGenerator.generateTrap(trapLoc.x * 2 + 1, trapLoc.y * 2 + 1, trapTile);
        }
    }
}
