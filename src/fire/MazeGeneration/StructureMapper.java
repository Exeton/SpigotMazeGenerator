package fire.MazeGeneration;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

public class StructureMapper {
    Vector origin;
    Vector structureSize;
    Material[][][] structureMap;
    public StructureMapper(Vector origin, Vector structureSize){
        this.origin = origin;
        this.structureSize = structureSize;
        structureMap = new Material[structureSize.getBlockX()][structureSize.getBlockY()][structureSize.getBlockZ()];
    }

    //All functions use coords relative to the structure
    public void setBlock(Vector location, Material material){
        structureMap[location.getBlockX()][location.getBlockY()][location.getBlockZ()] = material;
    }

    public void populateChunk(Chunk chunk){
        Vector chunkLocation = new Vector(16 * chunk.getX(), 0, 16 * chunk.getZ());
        Vector distanceFromStructureOrigin = origin.clone().subtract(chunkLocation);//Does this modify the value if you don't clone?
        for (int x = 0; x < 16; x++){
            for (int y = 0; y < 255; y++){
                for (int z = 0; z < 16; z++){
                    Vector locInStructureArray = distanceFromStructureOrigin.clone().add(new Vector(x,y,z));
                    if (insideStructure(locInStructureArray)){
                        chunkBlockFromVector(chunk, new Vector(x,y,z)).setType(materialFromStructureArray(locInStructureArray));
                    }
                }
            }
        }
    }
    public Material materialFromStructureArray(Vector loc){
        return structureMap[loc.getBlockX()][loc.getBlockY()][loc.getBlockZ()];
    }
    boolean insideStructure(Vector location){
        return insideArrayDimension(location.getBlockX(), 0)
                && insideArrayDimension(location.getBlockY(), 1)
                && insideArrayDimension(location.getBlockZ(), 2);
    }
    boolean insideArrayDimension(int value, int dimension){
        //throw new Exception("To many dimensions.");
        if (dimension == 2)
            return 0 <= value && value <= structureMap[0][1].length;
        return 0 <= value && value <= structureMap[dimension].length;
    }

    public static Block chunkBlockFromVector(Chunk chunk, Vector location){
        return chunk.getBlock(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }
}
