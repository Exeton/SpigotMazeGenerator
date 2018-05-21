package fire.MazeGeneration;

import org.bukkit.Chunk;

public class MazeInfo {
    public int worldCoordX;
    public int worldCoordZ;
    public int xLength;
    public int zLength;

    public MazeInfo(int xLength, int zLength){
        this.xLength = xLength;
        this.zLength = zLength;
    }
    public int toWorldPosZ(int mazeCoord){
        return mazeCoordToWorldPos(mazeCoord, worldCoordZ);
    }
    public int toWorldPosX(int mazeCoord){
        return mazeCoordToWorldPos(mazeCoord, worldCoordX);
    }
    private static int mazeCoordToWorldPos(int mazeCoord, int mazeLocationInWorld){
        return mazeLocationInWorld + 2 * mazeCoord + 1;
    }
    public boolean hsMazeStructure(Chunk chunk){
        boolean insideXBounds = axisHasMazeStructure(chunk.getX(), xLength, worldCoordX + worldCoordZ);
        boolean insideZBounds = axisHasMazeStructure(chunk.getZ(), zLength, worldCoordZ + worldCoordZ);

        return insideXBounds && insideZBounds;
    }
    private boolean axisHasMazeStructure(int chunkAxisValue, int mazeLengthOnAxis, int mazeOriginOnAxis){
        int chunkOrigin = chunkAxisValue * 16;
        if (mazeOriginOnAxis <= chunkOrigin && chunkOrigin <= mazeOriginOnAxis + mazeLengthOnAxis){
            return true;
        }
        return false;
    }
}
