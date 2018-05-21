package fire.MazeGeneration.MazePopulators;

import fire.MazeGeneration.StructureMapper;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

public class MappedStructurePopulator extends BlockPopulator {
    StructureMapper structureMapper;
    public MappedStructurePopulator(StructureMapper structureMapper){
        this.structureMapper = structureMapper;
    }

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        structureMapper.populateChunk(chunk);
    }
}
