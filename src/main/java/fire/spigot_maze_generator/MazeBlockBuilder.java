package fire.spigot_maze_generator;

import fire.spigot_maze_generator.maze_algorthim.DirectionUtil;
import fire.spigot_maze_generator.maze_algorthim.MazeGenerator;
import fire.spigot_maze_generator.maze_algorthim.MazeTile;
import org.bukkit.util.Vector;

public class MazeBlockBuilder {
    private MazeGenerator mazeGenerator;
    private BuildingGenerator buildingGenerator;

    public MazeBlockBuilder(BuildingGenerator buildingGenerator, MazeGenerator mazeGenerator){
        this.buildingGenerator = buildingGenerator;
        this.mazeGenerator = mazeGenerator;
    }
    public void fillStructureMap(int mazeLenX, int mazeLenZ){
        MazeTile[][] maze = mazeGenerator.nextMaze(mazeLenX, mazeLenZ);
        int mazeTilesX = maze.length;
        int mazeTilesZ = maze[0].length;

        clearMazeArea(mazeTilesX, mazeTilesZ);
        makeFloor(mazeTilesX, mazeTilesZ);
        makeMazeBorder(maze);
        makeInteriorMazeWalls(maze);
    }
    private void clearMazeArea(int mazeTilesX, int mazeTilesZ){
        Vector mazeRoomSize = new Vector(mazeTilesToBlocks(mazeTilesX), buildingGenerator.wallHeight + 1, mazeTilesToBlocks(mazeTilesZ));
        buildingGenerator.clearRoom(new Vector(0,buildingGenerator.buildingElevation,0), mazeRoomSize);
    }
    private void makeFloor(int mazeTilesX, int mazeTilesZ){
        buildingGenerator.makeFloor(0,0, mazeTilesToBlocks(mazeTilesX), mazeTilesToBlocks(mazeTilesZ));
    }
    private void makeMazeBorder(MazeTile[][] maze){
        buildingGenerator.makeWall(2 * maze.length, 0, DirectionUtil.Direction.South, mazeTilesToBlocks(maze[0].length));
        buildingGenerator.makeWall(0, 2 * maze[0].length, DirectionUtil.Direction.East, mazeTilesToBlocks(maze.length));
    }
    private void makeInteriorMazeWalls(MazeTile[][] maze){
        for (int x = 0; x < maze.length; x++)
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
