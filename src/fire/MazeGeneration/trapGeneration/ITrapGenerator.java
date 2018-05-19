package fire.MazeGeneration.trapGeneration;

import fire.MazeGeneration.MazeAlgorthim.MazeTile;

public interface ITrapGenerator {
    void generateTrap(int x, int z, MazeTile[] tiles);//todo Replace MazeTile[] with MazeSection class
}
