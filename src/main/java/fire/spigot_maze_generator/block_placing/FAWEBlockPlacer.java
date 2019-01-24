package fire.spigot_maze_generator.block_placing;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.blocks.BaseBlock;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.util.Vector;

public class FAWEBlockPlacer implements IBlockPlacer {

    EditSession editSession;

    int offsetX, offsetY, offsetZ = 0;

    public void resetSession(World world, int blocks){
        resetSession(world, blocks, 0,0,0);
    }

    public void resetSession(World world, int blocks, int OffsetX, int OffsetY, int OffsetZ){
        editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(new BukkitWorld(world), blocks);
        offsetX = OffsetX;
        offsetY = OffsetY;
        offsetZ = OffsetZ;
    }

    @Override
    public void setBlock(Vector location, Material blockType) {

        BaseBlock baseBlock = new BaseBlock(blockType.getId());

        try {
            editSession.setBlock(new com.sk89q.worldedit.Vector(
                    location.getBlockX() + offsetX,
                    location.getBlockY() + offsetY,
                    location.getBlockZ() + offsetZ), baseBlock);

        } catch (MaxChangedBlocksException e) {
            e.printStackTrace();
        }
    }

    public void applyChanges(){
        editSession.flushQueue();
    }
}
