package fire.MazeGeneration.MazeAlgorthim;

import java.awt.*;

public class MazeSection {
    public MazeTile[][] tiles;
    int startX;
    int startZ;

    public boolean hasXWall;
    public boolean hasZWall;

    public MazeSection(MazeTile[][] maze, int StartX, int StartZ, int lengthX, int lenghtZ){

    }
    public boolean hasTilesOrWalls() {
        return hasXWall || hasZWall || tiles.length > 0;
    }
}
