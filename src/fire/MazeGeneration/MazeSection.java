package fire.MazeGeneration;

import fire.MazeGeneration.MazeAlgorthim.MazeTile;

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

    public static Point toWorldCoords(Point mazeCoords){
        int worldX = toWorldCoord(mazeCoords.x);
        int worldZ = toWorldCoord(mazeCoords.y );
        return new Point(worldX, worldZ);
    }

    public static int toWorldCoord(int mazeCoord){
        return mazeCoord * 2 + 1;
    }
}
