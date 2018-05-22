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
    //Todo clean up building calls
    public void fillStructureMap(){

        int buildingElevation = buildingGenerator.buildingElevation;
        MazeTile[][] maze = mazeGenerator.generateMaze();
        for (int x = 0; x < maze[0].length; x++){
            for (int z = 0; z < maze[1].length; z++){
                int tileCenterX = 2 * x + 1;
                int tileCenterZ = 2 * z + 1;

                buildingGenerator.makePillar(new Vector(tileCenterX - 1 , buildingElevation + 1, tileCenterZ - 1), 3);
                buildingGenerator.setBlockType(new Vector(tileCenterX - 1, buildingElevation, tileCenterZ - 1), Material.STONE);
                buildingGenerator.setBlockType(new Vector(tileCenterX, buildingElevation, tileCenterZ), Material.STONE);
                buildingGenerator.setBlockType(new Vector(tileCenterX - 1, buildingElevation, tileCenterZ), Material.STONE);
                buildingGenerator.setBlockType(new Vector(tileCenterX, buildingElevation, tileCenterZ - 1), Material.STONE);
                if (maze[x][z].hasWall(DirectionUtil.Direction.North)){
                    buildingGenerator.makePillar(new Vector(tileCenterX , buildingElevation + 1, tileCenterZ - 1), 3);
                }
                if (maze[x][z].hasWall(DirectionUtil.Direction.West)){
                    buildingGenerator.makePillar(new Vector(tileCenterX - 1, buildingElevation + 1, tileCenterZ), 3);
                }
            }
            buildingGenerator.makePillar(new Vector(2 * x, buildingElevation + 1,  2 * maze[1].length), 3);
            buildingGenerator.makePillar(new Vector(2 * x + 1, buildingElevation + 1,  2 * maze[1].length), 3);
            buildingGenerator.setBlockType(new Vector(2 * x, buildingElevation, 2 * maze[1].length - 1), Material.STONE);
            buildingGenerator.setBlockType(new Vector(2 * x + 1, buildingElevation, 2 * maze[1].length - 1), Material.STONE);
        }


        for (int i = 0; i < 2 * maze[0].length + 1; i++){
            buildingGenerator.makePillar(new Vector(2 * maze[0].length, buildingElevation + 1,  i), 3);
            buildingGenerator.setBlockType(new Vector(2 * maze[0].length - 1, buildingElevation, i),  Material.STONE);
        }
    }
    public static int blocksToCells(int blocks){
        return (blocks - 1) / 2;
    }
}
