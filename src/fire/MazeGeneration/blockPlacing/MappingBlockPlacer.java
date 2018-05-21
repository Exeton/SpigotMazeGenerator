package fire.MazeGeneration.blockPlacing;

import fire.MazeGeneration.StructureMapper;
import org.bukkit.Material;
import org.bukkit.util.Vector;

public class MappingBlockPlacer implements  IBlockPlacer{

    StructureMapper structureMapper;
    public MappingBlockPlacer(StructureMapper structureMapper){
        this.structureMapper = structureMapper;
    }
    @Override
    public void setBlock(Vector location, Material blockType) {
        structureMapper.setBlock(location, blockType);
    }
}
