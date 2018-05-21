package fire.MazeGeneration.MazePopulators;

import fire.MazeGeneration.BuildingGenerator;
import fire.MazeGeneration.MazeInfo;
import org.bukkit.*;
import org.bukkit.generator.BlockPopulator;

import fire.MazeGeneration.MazeAlgorthim.*;
import org.bukkit.util.Vector;

import static fire.MazeGeneration.MazeAlgorthim.DirectionUtil.Direction;

import java.util.Random;

public class MazeStructurePopulator extends BlockPopulator {

    //Lengths must be 16n + 1, where n is a positive integer
    private int xLength = 33;
    private int zLength = 33;
    private MazeGenerator mazeGenerator = new MazeGenerator(blocksToCells(xLength), blocksToCells(zLength));


    private MazeInfo mazeInfo = new MazeInfo(xLength, zLength);

    private int numOfCellsX;
    private int numOfCellsZ;
    public BuildingGenerator buildingGenerator;

    public MazeStructurePopulator(BuildingGenerator buildingGenerator)
    {
        this.buildingGenerator =buildingGenerator;

        Bukkit.getLogger().info("Generating maze");
        mazeGenerator.generateMaze();
        Bukkit.getLogger().info("Done!");
    }

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        // Ensure that buildingGenerator#setChunk(chunk) is called
        generateMazeSection(world, random, chunk);
    }

    public void generateMazeSection(World world, Random random, Chunk chunk){
        buildingGenerator.setChunk(chunk);//Do not move. This must be called at the start of every populate
        if (!mazeInfo.hsMazeStructure(chunk)){
            return;
        }
        MazeTile[][] sectionOfMaze = mazeGenerator.getMazeTiles(8 * chunk.getX(),8 * chunk.getZ() , 8,8);
        updateNumberOfCells(sectionOfMaze);

        //Todo refactor into a new method (When adding support for maze tile lengths that aren't a multiple of 8)
        if (numOfCellsX == 0){
            Bukkit.getLogger().info("Making maze border {" + chunk.getX() + "," + chunk.getZ()+ "}");
            makeMazeWalls(chunk);
            return;
        }
        buildingGenerator.makeChunkFloor(numOfCellsX *2, numOfCellsZ * 2);
        buildingGenerator.makeGrid(numOfCellsX * 2, numOfCellsZ * 2);
        carveWalls(sectionOfMaze);
    }

    private void carveWalls(MazeTile[][] sectionOfMaze){
        //Carve out odd blocks
        for (int mazeX = 0; mazeX < numOfCellsX; mazeX++){
            for (int mazeZ = 0; mazeZ < numOfCellsZ; mazeZ++) {
                int worldX = 2 * mazeX + 1;
                int worldZ =  2 * mazeZ + 1;
                if (!sectionOfMaze[mazeX][mazeZ].hasWall(Direction.North)){
                    buildingGenerator.clearPillar(new Vector(worldX , 101, worldZ - 1), 3);
                }
                if (!sectionOfMaze[mazeX][mazeZ].hasWall(Direction.West)){
                    buildingGenerator.clearPillar(new Vector(worldX -1, 101, worldZ), 3);
                }
            }
        }
    }
    private void updateNumberOfCells(MazeTile[][] sectionOfMaze){
        if (sectionOfMaze.length == 0){
            numOfCellsX = 0;
            numOfCellsZ = 0;
            return;
        }
        numOfCellsX = sectionOfMaze[0].length;
        numOfCellsZ = sectionOfMaze[1].length;
    }
    private void makeMazeWalls(Chunk chunk){
            boolean wallEast = shouldMakeWall(zLength + mazeInfo.worldCoordZ, chunk.getZ());
            boolean wallSouth = shouldMakeWall(xLength + mazeInfo.worldCoordX, chunk.getX());;

            if (wallEast && wallSouth)//Corner chunk
                buildingGenerator.makePillar(0, 0, 3);
            else if (wallEast)
                buildingGenerator.makeWall(0,0,Direction.East, 16);
            else if (wallSouth)
                buildingGenerator.makeWall(0,0,Direction.South, 16);
    }
    private boolean shouldMakeWall(int endOfWallCoord, int chunkCoord){
        int chunkStart = 16 * chunkCoord;
        int chunkEnd = chunkStart + 15;
        return (chunkStart <= endOfWallCoord && endOfWallCoord <= chunkEnd);
    }

    private static int blocksToCells(int blocks){
        return (blocks - 1) / 2;
    }
}
