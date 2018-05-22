package fire.MazeGeneration;

import fire.MazeGeneration.MazeAlgorthim.DirectionUtil;
import fire.MazeGeneration.MazeAlgorthim.MazeGenerator;
import fire.MazeGeneration.MazeAlgorthim.MazeTile;
import org.bukkit.Bukkit;
import org.bukkit.Material;
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

        for (int x = 0; x < maze[0].length; x++){
            for (int z = 0; z < maze[1].length; z++){
                int tileCenterX = 2 * x + 1;
                int tileCenterZ = 2 * z + 1;

                makeTileFloor(x,z);
                buildingGenerator.makePillar(tileCenterX - 1 , tileCenterZ - 1, 3);//Corner of cell

                if (maze[x][z].hasWall(DirectionUtil.Direction.North)){
                    buildingGenerator.makePillar(tileCenterX , tileCenterZ - 1, 3);
                }
                if (maze[x][z].hasWall(DirectionUtil.Direction.West)){
                    buildingGenerator.makePillar(tileCenterX - 1,  tileCenterZ, 3);
                }
            }

            //Make maze perimeter
            buildingGenerator.makePillar(2 * x, 2 * maze[1].length, 3);
            buildingGenerator.makePillar(2 * x + 1,2 * maze[1].length, 3);
            makeTileFloor(x, maze[1].length - 1);
        }
        //Make maze perimeter
        buildingGenerator.makeWall(2 * maze[0].length, 0, DirectionUtil.Direction.South, 2 * maze[0].length);
    }
    private void makeTileFloor(int mazeTileX, int mazeTileZ){
        buildingGenerator.makeFloor(mazeTileX * 2, mazeTileZ * 2, 2, 2);
    }

    public static int blocksToCells(int blocks){
        return (blocks - 1) / 2;
    }
}
