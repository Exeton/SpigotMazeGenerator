package fire.MazeGeneration.MazeAlgorthim

import org.junit.Assert
import org.junit.Test

class MazeGeneratorTest extends GroovyTestCase {


    @Test
    void testNextMazeSize() throws Exception{
        MazeGenerator mazeGenerator = new MazeGenerator(33, 65, new Random());
        MazeTile[][] maze = mazeGenerator.nextMaze();

        boolean mazeIsProperSize = (maze.length == 33) && (maze[0].length == 65);
        Assert.assertTrue(mazeIsProperSize);
    }

    void testNextMaze() throws Exception{
        MazeGenerator mazeGenerator = new MazeGenerator(32, 32, new Random());

    }
}
