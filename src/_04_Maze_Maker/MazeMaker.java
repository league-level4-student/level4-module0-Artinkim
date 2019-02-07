package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.event.CellEditorListener;

public class MazeMaker {

	private static int width;
	private static int height;

	private static Maze maze;

	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	public static Maze generateMaze(int w, int h) {
		width = w;
		height = h;
		maze = new Maze(width, height);

		// 4. select a random cell to start
		int x = randGen.nextInt(maze.getWidth());
		int y = randGen.nextInt(maze.getHeight());
		// 5. call selectNextPath method with the randomly selected cell
		selectNextPath(maze.getCell(x, y));
		return maze;
	}

	// 6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		// A. mark cell as visited
		maze.ac[currentCell.getX()][currentCell.getY()].setBeenVisited(true);
		// B. check for unvisited neighbors using the cell
		ArrayList<Cell> sides = getUnvisitedNeighbors(currentCell);
		// C. if has unvisited neighbors,

		if (sides.size() > 0) {
			int num = randGen.nextInt(sides.size());
			for (int i = 0; i < sides.size(); i++) {
				if (i == num) {
					removeWalls(currentCell,sides.get(i));
					uncheckedCells.push(sides.get(i)); 
					currentCell = sides.get(i);
					selectNextPath(currentCell);
				}
				
			}
		} else {
			if (!uncheckedCells.isEmpty()) {
				currentCell = uncheckedCells.pop();
				selectNextPath(currentCell);
			}
		}

		// C1. select one at random.

		// C2. push it to the stack

		// C3. remove the wall between the two cells

		// C4. make the new cell the current cell and mark it as visited

		// C5. call the selectNextPath method with the current cell

		// D. if all neighbors are visited

		// D1. if the stack is not empty

		// D1a. pop a cell from the stack

		// D1b. make that the current cell

		// D1c. call the selectNextPath method with the current cell

	}

	// 7. Complete the remove walls method.
	// This method will check if c1 and c2 are adjacent.
	// If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if (c1.getX() < width - 1) {
			if (maze.ac[c1.getX() + 1][c1.getY()] == maze.ac[c2.getX()][c2.getY()]) {
				c1.setEastWall(false);
				c2.setWestWall(false);
			}
		}
		if (c1.getX() > 0) {
			if (maze.ac[c1.getX() - 1][c1.getY()] == maze.ac[c2.getX()][c2.getY()]) {
				c1.setWestWall(false);
				c2.setEastWall(false);
			}
		}
		if (c1.getY() < height - 1) {
			if (maze.ac[c1.getX()][c1.getY()+1] == maze.ac[c2.getX()][c2.getY()]) {
				c1.setSouthWall(false);
				c2.setNorthWall(false);
			}
		}
		if (c1.getY() > 0) {
			if (maze.ac[c1.getX()][c1.getY()-1] == maze.ac[c2.getX()][c2.getY()]) {
				c1.setNorthWall(false);
				c2.setSouthWall(false);
			}
		}
	}
	

	// 8. Complete the getUnvisitedNeighbors method
	// Any unvisited neighbor of the passed in cell gets added
	// to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell currentCell) {
		ArrayList<Cell> cells = new ArrayList<Cell>();
		if (currentCell.getX() < width - 1) {
			if (!maze.ac[currentCell.getX() + 1][currentCell.getY()].hasBeenVisited()) {
				cells.add(maze.ac[currentCell.getX() + 1][currentCell.getY()]);
			}
		}
		if (currentCell.getX() > 0) {
			if (!maze.ac[currentCell.getX() - 1][currentCell.getY()].hasBeenVisited()) {
				cells.add(maze.ac[currentCell.getX() - 1][currentCell.getY()]);
			}
		}
		if (currentCell.getY() < height - 1) {
			if (!maze.ac[currentCell.getX()][currentCell.getY() + 1].hasBeenVisited()) {
				cells.add(maze.ac[currentCell.getX()][currentCell.getY() + 1]);
			}
		}
		if (currentCell.getY() > 0) {
			if (!maze.ac[currentCell.getX()][currentCell.getY() - 1].hasBeenVisited()) {
				cells.add(maze.ac[currentCell.getX()][currentCell.getY() - 1]);
			}
		}
		return cells;
	}
}
