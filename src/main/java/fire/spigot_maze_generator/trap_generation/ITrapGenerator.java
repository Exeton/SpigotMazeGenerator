package fire.spigot_maze_generator.trap_generation;

import fire.spigot_maze_generator.maze_algorthim.MazeTile;

public interface ITrapGenerator {
    void generateTrap(int x, int z, MazeTile tiles);//todo Replace MazeTile[] with MazeSection class
}
