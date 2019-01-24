package fire.spigot_maze_generator;

import fire.spigot_maze_generator.block_placing.IBlockPlacer;
import org.bukkit.Material;
import org.bukkit.util.Vector;


import static fire.spigot_maze_generator.maze_algorthim.DirectionUtil.Direction;

public class BuildingGenerator {
    public int buildingElevation;
    public int wallHeight;
    public Material wallMaterial;
    public Material floorMaterial;
    private IBlockPlacer blockPlacer;

    public Vector buildingHeightAsVector(){
        return new Vector(0, buildingElevation, 0);
    }

    public BuildingGenerator(IBlockPlacer blockPlacer, int buildingElevation, int wallHeight, Material wallMaterial, Material floorMaterial){
        this.blockPlacer = blockPlacer;
        this.wallMaterial = wallMaterial;
        this.floorMaterial = floorMaterial;
        this.buildingElevation = buildingElevation;
        this.wallHeight = wallHeight;
    }

    public void fillRoom(Vector startPos, Vector roomSize, Material m){
        for (int x = 0; x < roomSize.getBlockX(); x++)
            for (int y = 0; y < roomSize.getBlockY(); y++)
                for (int z = 0; z < roomSize.getBlockZ(); z++){
                    Vector newPos = startPos.clone().add(new Vector(x,y,z));
                    setBlockType(newPos, m);
                }
    }
    public void clearRoom(Vector startPos, Vector roomSize){
        fillRoom(startPos, roomSize, Material.AIR);
    }

    public void makeWall(int startX, int startZ, Direction direction, int wallLength, Material material){
        Vector position = new Vector(startX, buildingElevation + 1, startZ);
        Vector directionVector = direction.getDirectionVector();
        for (int i = 0; i < wallLength; i++){
            makePillar(position, wallHeight, material);
            position = position.add(directionVector);
        }
    }
    public void makeWall(int startX, int startZ, Direction direction, int wallLength){
        makeWall(startX, startZ, direction, wallLength, wallMaterial);
    }
    public void makeFloor(int startX, int startZ, int lengthX, int lengthZ){
        for (int x = 0; x  < lengthX; x++){
            for (int z = 0; z <lengthZ; z++){
                setBlockType(new Vector(startX + x, buildingElevation, startZ + z), floorMaterial);
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
            blockPlacer.setBlock(position, material);
            position.add(upwardsVector);
        }
    }
    public void setBlockType(Vector location, Material blockType){
        blockPlacer.setBlock(location, blockType);
    }
   }
