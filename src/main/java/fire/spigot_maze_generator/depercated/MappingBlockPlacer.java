package fire.spigot_maze_generator.depercated;

import fire.spigot_maze_generator.StructureMapper;
import fire.spigot_maze_generator.block_placing.IBlockPlacer;
import org.bukkit.Material;
import org.bukkit.util.Vector;

public class MappingBlockPlacer implements IBlockPlacer {

    StructureMapper structureMapper;
    public MappingBlockPlacer(StructureMapper structureMapper){
        this.structureMapper = structureMapper;
    }
    @Override
    public void setBlock(Vector location, Material blockType) {
        structureMapper.setBlock(location, blockType);
    }
}
