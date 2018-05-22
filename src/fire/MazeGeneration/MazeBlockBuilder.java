package fire.MazeGeneration;

import fire.MazeGeneration.MazeAlgorthim.DirectionUtil;
import fire.MazeGeneration.MazeAlgorthim.MazeGenerator;
import fire.MazeGeneration.MazeAlgorthim.MazeTile;
import org.bukkit.util.Vector;

public class MazeBlockBuilder {
    private MazeGenerator mazeGenerator;
    private BuildingGenerator buildingGenerator;

    public MazeBlockBuilder(BuildingGenerator buildingGenerator, MazeGenerator mazeGenerator){
        this.buildingGenerator = buildingGenerator;
        this.mazeGenerator = mazeGenerator;
    }
    public void fillStructureMap(){
        MazeTile[][] maze = mazeGenerator.generateMaze();
        int mazeTilesX = maze[0].length;
        int mazeTilesZ = maze[1].length;

        clearMazeArea(mazeTilesX, mazeTilesZ);
        makeFloor(mazeTilesX, mazeTilesZ);
        makeMazeBorder(maze);
        makeInteriorMazeWalls(maze);
    }
    private void clearMazeArea(int mazeTilesX, int mazeTilesZ){
        Vector mazeRoomSize = new Vector(mazeTilesToBlocks(mazeTilesX), buildingGenerator.buildingElevation + 1, mazeTilesToBlocks(mazeTilesZ));
        buildingGenerator.clearRoom(new Vector(0,buildingGenerator.buildingElevation -1,0), mazeRoomSize);
    }
    private void makeFloor(int mazeTilesX, int mazeTilesZ){
        buildingGenerator.makeFloor(0,0, mazeTilesToBlocks(mazeTilesX), mazeTilesToBlocks(mazeTilesZ));
    }
    private void makeMazeBorder(MazeTile[][] maze){
        buildingGenerator.makeWall(2 * maze[0].length, 0, DirectionUtil.Direction.South, mazeTilesToBlocks(maze[1].length));
        buildingGenerator.makeWall(0, 2 * maze[1].length, DirectionUtil.Direction.East, mazeTilesToBlocks(maze[0].length));
    }
    private void makeInteriorMazeWalls(MazeTile[][] maze){
        for (int x = 0; x < maze[0].length; x++)
            for (int z = 0; z < maze[1].length; z++){
                int tileCenterX = mazeTilesToBlocks(x);
                int tileCenterZ = mazeTilesToBlocks(z);

                buildingGenerator.makePillar(tileCenterX - 1 , tileCenterZ - 1, 3);//Corner of cell
                if (maze[x][z].hasWall(DirectionUtil.Direction.North)){
                    buildingGenerator.makePillar(tileCenterX , tileCenterZ - 1, 3);
                }
                if (maze[x][z].hasWall(DirectionUtil.Direction.West)){
                    buildingGenerator.makePillar(tileCenterX - 1,  tileCenterZ, 3);
                }
            }
    }

    public static int blocksToCells(int blocks){
        return (blocks - 1) / 2;
    }
    public static int mazeTilesToBlocks(int mazeTiles){
        return 2 * mazeTiles + 1;
    }
}
