package fire.MazeGeneration.blockPlacing;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

public class ChunkBlockPlacer implements IBlockPlacer{
    Chunk currentChunk;

    public void updateCurrentChunk(Chunk newChunk){
        currentChunk = newChunk;
    }
    @Override
    public void setBlock(Vector vector, Material blockType){
        chunkBlockFromVector(currentChunk, vector).setType(blockType);
    }
    public Block chunkBlockFromVector(Chunk chunk, Vector pos){
        return chunk.getBlock(pos.getBlockX(), pos.getBlockY(), pos.getBlockZ());
    }
}
