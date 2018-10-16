package fire.MazeGeneration.MazeAlgorthim

import org.junit.Assert
import org.junit.Test

import java.awt.Point

class MazeGeneratorTest extends GroovyTestCase {


    @Test
    void testNextMazeSize() throws Exception{
        MazeGenerator mazeGenerator = new MazeGenerator(33, 65, new Random());
        MazeTile[][] maze = mazeGenerator.nextMaze();

        boolean mazeIsProperSize = (maze.length == 33) && (maze[0].length == 65);
        Assert.assertTrue(mazeIsProperSize);
    }

    @Test
    void testEveryMazeTileIsAccessible() throws Exception{

        //Incomplete
        int sizeX = 33;
        int sizeZ = 33;

        MazeGenerator mazeGenerator = new MazeGenerator(sizeX, sizeZ, new Random());

        MazeTile[][] maze = mazeGenerator.nextMaze();
        List<MazeTile> explored = new ArrayList<MazeTile>(maze.length * maze[0].length);
        List<Point> frontier = new LinkedList<Point>();
        frontier.add(new Point(0,0));

        while (frontier.size() > 0){
            List<Point> newFrontier = new LinkedList<Point>();

            for (Point frontierTile in frontier){

                //Can travel method required for mazeTile
                //Check all unexplored tiles. If it's unexplored, add it to the explored tiles, and new frontier

            }

            frontier = newFrontier;
        }

        Assert.assertTrue(explored.size() == sizeX * sizeZ);
    }

}
