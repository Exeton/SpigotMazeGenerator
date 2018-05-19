package fire.MazeGeneration;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.TreeSpecies;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;


import static fire.MazeGeneration.MazeAlgorthim.DirectionUtil.Direction;

public class BuildingGenerator {
    int buildingElevation;
    int wallHeight;
    public Material wallMaterial;
    public Material floorMaterial;
    Chunk chunk;

    public Vector buildingHeightAsVector(){
        return new Vector(0, buildingElevation, 0);
    }

    public BuildingGenerator(int buildingElevation, int wallHeight, Material wallMaterial, Material floorMaterial){
        this.wallMaterial = wallMaterial;
        this.floorMaterial = floorMaterial;
        this.buildingElevation = buildingElevation;
        this.wallHeight = wallHeight;
    }
    public void setChunk(Chunk chunk){
        this.chunk = chunk;
    }
    public void makeWall(int startX, int startZ, Direction direction, int wallLength){
        Vector position = new Vector(startX, buildingElevation + 1, startZ);
        Vector directionVector = direction.getDirectionVector();
        for (int i = 0; i < wallLength; i++){
            makePillar(position, wallHeight);
            position.add(directionVector);
        }
    }
    public void makeChunkFloor(int lengthX, int lengthZ){
        for (int x = 0; x  < lengthX; x++){
            for (int z = 0; z <lengthZ; z++){
                chunk.getBlock(x, buildingElevation, z).setType(floorMaterial);
            }
        }
    }
    public void makeGrid(int xLength, int zLength){
        //Make walls
        for (int x = 0; x < xLength; x++) {
            for (int z = 0; z < zLength; z++) {
                if (isEven(x) || isEven(z)) {
                    makePillar(new Vector(x, buildingElevation + 1, z), wallHeight);
                }
            }
        }
    }

    public void makePillar(int x, int z, int height){
        makePillar(new Vector(x, buildingElevation + 1, z), height, wallMaterial);
    }
    public void makePillar(Vector bottomBlock, int height){
        makePillar(bottomBlock, height, wallMaterial);
    }
    public void clearPillar(Vector bottomBlock, int height){
        makePillar(bottomBlock, height, Material.AIR);
    }
    public void makePillar(Vector bottomBlock, int height, Material material){
        Vector position = bottomBlock.clone();//We don't want to modify the inputted vector
        Vector upwardsVector = new Vector(0,1,0);
        for (int i = 0; i < height; i++){
            chunkBlockFromVector(chunk, position).setType(material);
            position.add(upwardsVector);
        }
    }

    public void setBlockType(Vector vector, Material blockType){
        chunkBlockFromVector(chunk, vector).setType(blockType);
    }
    public Block chunkBlockFromVector(Chunk chunk, Vector pos){
        return chunk.getBlock(pos.getBlockX(), pos.getBlockY(), pos.getBlockZ());
    }


    private static boolean isEven(int x){
        return ((x & 1) == 0);
    }
}
