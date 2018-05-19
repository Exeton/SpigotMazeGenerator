package fire.MazeGeneration.MazePopulators;

import fire.MazeGeneration.BuildingGenerator;
import fire.MazeGeneration.MazeSection;
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


    private int mazeOriginX = 0;
    private int mazeOriginZ = 0;

    private int numOfCellsX;
    private int numOfCellsZ;
    Random r = new Random();
    public BuildingGenerator buildingGenerator = new BuildingGenerator(100, 3, Material.WOOD, Material.STONE);

    public MazeStructurePopulator()
    {
        Bukkit.getLogger().info("Generating maze");
        mazeGenerator.generateMaze();
        Bukkit.getLogger().info("Done!");
    }

    private boolean chunckHasMazeStructure(Chunk chunk){
        boolean insideXBounds = axisHasMazeStructure(chunk.getX(), xLength , mazeOriginX);
        boolean insideZBounds = axisHasMazeStructure(chunk.getZ(), zLength, mazeOriginZ);

        return insideXBounds && insideZBounds;
    }
    private boolean axisHasMazeStructure(int chunkAxisValue, int mazeLengthOnAxis, int mazeOriginOnAxis){
        int chunkOrigin = chunkAxisValue * 16;
        if (mazeOriginOnAxis <= chunkOrigin && chunkOrigin <= mazeOriginOnAxis + mazeLengthOnAxis){
            return true;
        }
        return false;
    }

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        generateMazeSection(world, random, chunk);
    }

    public void generateMazeSection(World world, Random random, Chunk chunk){
        if (!chunckHasMazeStructure(chunk)){
            return;
        }
        MazeTile[][] sectionOfMaze = mazeGenerator.getMazeTiles(8 * chunk.getX(),8 * chunk.getZ() , 8,8);
        updateNumberOfCells(sectionOfMaze);

        //Todo refactor into a new method (When adding support for maze tile lengths that aren't a multiple of 8)
        if (numOfCellsX == 0){
            makeMazeWalls(chunk, sectionOfMaze);
            return;
        }

        buildingGenerator.setChunk(chunk);
        buildingGenerator.makeChunkFloor(numOfCellsX *2, numOfCellsZ * 2);
        buildingGenerator.makeGrid(numOfCellsX * 2, numOfCellsZ * 2);

        //Todo fix wall carving
        carveWalls(sectionOfMaze);
    }

    private void carveWalls(MazeTile[][] sectionOfMaze){
        //Carve out odd blocks
        for (int mazeX = 0; mazeX < 8; mazeX++){
            for (int mazeZ = 0; mazeZ < 8; mazeZ++) {
                int worldX = MazeSection.toWorldCoord(mazeX);
                int worldZ =  MazeSection.toWorldCoord(mazeZ);
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
    private void makeMazeWalls(Chunk chunk, MazeTile[][] sectionOfMaze){
            int mazeWallX = xLength + mazeOriginX;
            int mazeWallZ = zLength + mazeOriginZ;

            boolean wallEast;
            boolean wallSouth;
            //Check if the position of the wall would be less than the wall if the wall was moved over 1 chunk.
            //This is only true for chunks with a maze wall, or chunks farther away from the origin than the current chunk
            //(We already check that each chunk is in the maze)

            wallEast =(16 * (chunk.getZ() + 1)  > mazeWallZ);
            wallSouth = (16 * (chunk.getX() + 1)  > mazeWallX);

            if (wallEast && wallSouth)//Corner chunk
                buildingGenerator.makePillar(0, 0, 3);
            else if (wallEast)
                buildingGenerator.makeWall(0,0,Direction.East, 16);
            else if (wallSouth)
                buildingGenerator.makeWall(0,0,Direction.South, 16);
    }
    private static int blocksToCells(int blocks){
        return (blocks - 1) / 2;
    }
}
