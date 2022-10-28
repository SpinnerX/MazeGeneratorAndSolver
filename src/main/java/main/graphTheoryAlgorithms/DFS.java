package main.graphTheoryAlgorithms;

import main.linkedLists.LinkedList;
import main.linkedLists.Node;
import main.mazes.Coordinate;
import main.mazes.Maze;

public class DFS {
    /**
     * A depth first search algorithm that goes through a maze
     * @param maze              the maze we are searching through
     * @param visited           the visited coordinates
     * @param currentPosition   current position in the maze
     * @return                  the solution of the maze
     */
    public static LinkedList DepthFirstSearch(Maze maze, boolean[][] visited, Coordinate currentPosition) {
        int x = currentPosition.getX();
        int y = currentPosition.getY();
        int currentNode = maze.node(x,y);

        // grab next tile from maze at current node and store it
        Node nextTile = maze.getMazeGraph().getAdjList()[currentNode].getHead();
        while (nextTile != null) {
            // grab next coordinates
            Coordinate nextTileCoords = maze.position(nextTile.getItem());

            // if next tile is the end of the maze
            if (nextTile.getItem() == maze.getEndingTile()) {
                return backTrack(maze, nextTileCoords);
            }

            // if current position hasn't been visited yet
            if (!visited[x][y]) {
                visited[x][y] = true;
                return DepthFirstSearch(maze, visited, nextTileCoords);
            }
            nextTile = nextTile.getNext();
        }
        return null;
    }

    /**
     * Backtracks through the maze to return the solution of the maze
     * @param maze              maze we are backtracking through
     * @param currentPosition   current position in the maze
     * @return                  the solution path of the maze
     */
    private static LinkedList backTrack(Maze maze, Coordinate currentPosition) {
        Node tile = maze.getMazeGraph().getAdjList()[maze.node(currentPosition.getX(), currentPosition.getY())].getHead();
        LinkedList path = new LinkedList();
        path.add(maze.getEndingTile());
        while (tile.getItem() != maze.getStartingTile()) {
            if (tile.getNext() == null){
                path.add(tile.getItem());
                tile = maze.getMazeGraph().getAdjList()[tile.getItem()].getHead();
            }
            tile = tile.getNext();
        }
        path.add(maze.getStartingTile());
        return path;
    }
}
