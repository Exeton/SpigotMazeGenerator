package fire.MazeGeneration.trapGeneration;

import fire.MazeGeneration.MazeAlgorthim.MazeTile;

import java.util.Random;

public class MultiTrapGenerator implements ITrapGenerator{

    ITrapGenerator[] generators;
    Random random = new Random();
    public MultiTrapGenerator(ITrapGenerator... trapGenerators){
        generators = trapGenerators;
    }
    @Override
    public void generateTrap(int x, int z, MazeTile tiles) {
        int generatorIndex = random.nextInt(generators.length);
        generators[generatorIndex].generateTrap(x,z,tiles);
    }
}
