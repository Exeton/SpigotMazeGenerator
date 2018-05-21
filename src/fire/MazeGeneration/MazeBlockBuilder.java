package fire.MazeGeneration;

import fire.MazeGeneration.MazeAlgorthim.DirectionUtil;
import fire.MazeGeneration.MazeAlgorthim.MazeGenerator;
import fire.MazeGeneration.MazeAlgorthim.MazeTile;
import org.bukkit.Material;
import org.bukkit.util.Vector;

//Todo Maze is getting cut off on four sides. Error in structure mapper?
public class MazeBlockBuilder {
    private int xLength = 33;
    private int zLength = 33;
    private MazeGenerator mazeGenerator = new MazeGenerator(blocksToCells(xLength), blocksToCells(zLength));
    private BuildingGenerator buildingGenerator;

    public MazeBlockBuilder(BuildingGenerator buildingGenerator){
        this.buildingGenerator = buildingGenerator;
    }
    public void fillStructureMap(){

        MazeTile[][] maze = mazeGenerator.generateMaze();
        for (int x = 0; x < maze[0].length; x++){
            for (int z = 0; z < maze[1].length; z++){
                int tileCenterX = 2 * x + 1;
                int tileCenterZ = 2 * z + 1;
                //Todo replace make floor class with cleaner code.
                buildingGenerator.makePillar(new Vector(tileCenterX - 1 , 101, tileCenterZ - 1), 3);
                buildingGenerator.setBlockType(new Vector(tileCenterX, 100, tileCenterZ), Material.STONE);
                buildingGenerator.setBlockType(new Vector(tileCenterX - 1, 100, tileCenterZ), Material.STONE);
                buildingGenerator.setBlockType(new Vector(tileCenterX, 100, tileCenterZ - 1), Material.STONE);
                if (maze[x][z].hasWall(DirectionUtil.Direction.North)){
                    buildingGenerator.makePillar(new Vector(tileCenterX , 101, tileCenterZ - 1), 3);
                }
                if (maze[x][z].hasWall(DirectionUtil.Direction.West)){
                    buildingGenerator.makePillar(new Vector(tileCenterX - 1, 101, tileCenterZ), 3);
                }
            }
        }
    }
    private static int blocksToCells(int blocks){
        return (blocks - 1) / 2;
    }
}
