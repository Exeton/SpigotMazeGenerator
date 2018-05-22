package fire.MazeGeneration.trapGeneration;

import fire.MazeGeneration.BuildingGenerator;
import fire.MazeGeneration.MazeAlgorthim.MazeTile;
import fire.MazeGeneration.blockPlacing.IBlockPlacer;

import java.awt.*;
import java.util.List;

import static fire.MazeGeneration.MazeBlockBuilder.blocksToCells;

public class MazeTrapPlacer {
    ITrapDistributor trapDistributor;
    ITrapGenerator trapGenerator;
    public MazeTrapPlacer(ITrapGenerator trapGenerator, int xLen, int zLen){
        //trapGenerator.generateTrap(x,z) should be called with the center of the maze cell (Not where a wall is)
        //To do this we call blocksToCells, then scale the trap coords back up when placing the trap
        this.trapDistributor = new UniformTrapDistributor(blocksToCells(xLen), blocksToCells(zLen));
        this.trapGenerator = trapGenerator;
    }
    public void addTraps(MazeTile[][] mazeTiles){
        List<Point> traps = trapDistributor.distributeTraps();
        for (Point trapLoc : traps){
            MazeTile trapTile = mazeTiles[trapLoc.x][trapLoc.y];
            trapGenerator.generateTrap(trapLoc.x * 2 + 1, trapLoc.y * 2 + 1, trapTile);
        }
    }
}
