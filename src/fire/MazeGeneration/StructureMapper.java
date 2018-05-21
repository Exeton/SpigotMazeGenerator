package fire.MazeGeneration;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

public class StructureMapper {
    private Vector origin;
    private Vector structureSize;
    private Material[][][] structureMap;
    public StructureMapper(Vector origin, Vector structureSize){
        this.origin = origin;
        this.structureSize = structureSize;
        structureMap = new Material[structureSize.getBlockX()][structureSize.getBlockY()][structureSize.getBlockZ()];
    }
    public void setBlock(Vector location, Material material){
        structureMap[location.getBlockX()][location.getBlockY()][location.getBlockZ()] = material;
    }

    public void populateChunk(Chunk chunk){
        //Todo Don't search chunks that deffos don't have structure.
        Vector chunkLocation = new Vector(16 * chunk.getX(), 0, 16 * chunk.getZ());
        Vector distanceFromStructureOrigin = chunkLocation.clone().subtract(origin);//Does this modify the value if you don't clone?
        for (int x = 0; x < 16; x++){
            for (int y = 0; y < 255; y++){
                for (int z = 0; z < 16; z++){
                    Vector locInStructureArray = distanceFromStructureOrigin.clone().add(new Vector(x,y,z));
                    if (!insideStructure(locInStructureArray)) {
                        continue;
                    }
                    Material blockMaterial = materialFromStructureArray(locInStructureArray);
                    if (blockMaterial != null)
                        chunkBlockFromVector(chunk, new Vector(x,y,z)).setType(blockMaterial);
                }
            }
        }
    }
    private Material materialFromStructureArray(Vector loc){
        return structureMap[loc.getBlockX()][loc.getBlockY()][loc.getBlockZ()];
    }
    private boolean insideStructure(Vector location){
        return insideArrayDimension(location.getBlockX(), 0)
                && insideArrayDimension(location.getBlockY(), 1)
                && insideArrayDimension(location.getBlockZ(), 2);
    }
    private boolean insideArrayDimension(int value, int dimension){

        if (dimension == 0)
            return 0 <= value && value < structureMap.length;
        if (dimension == 1)
            return 0 <= value && value < structureMap[0].length;
        if (dimension == 2)
            return 0 <= value && value < structureMap[0][0].length;
        return false;//Thrown unsuppourted dimension error
    }

    public static Block chunkBlockFromVector(Chunk chunk, Vector location){
        return chunk.getBlock(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }
}
