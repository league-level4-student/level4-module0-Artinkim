package _03_Conways_Game_of_Life;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class WorldPanel extends JPanel implements MouseListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private int cellsPerRow;
	private int cellSize;
	Random rand = new Random();
	private Timer timer;

	// 1. Create a 2D array of Cells. Do not initialize it.
	Cell[][] ac;

	public WorldPanel(int w, int h, int cpr) {
		setPreferredSize(new Dimension(w, h));
		addMouseListener(this);
		timer = new Timer(500, this);
		this.cellsPerRow = cpr;

		// 2. Calculate the cell size.
		cellSize = h / cpr;
		// 3. Initialize the cell array to the appropriate size.
		ac = new Cell[cpr][cpr];
		// 3. Iterate through the array and initialize each cell.
		// Don't forget to consider the cell's dimensions when
		// passing in the location.
		for (int i = 0; i < ac.length; i++) {
			for (int j = 0; j < ac[i].length; j++) {
				ac[i][j] = new Cell(i, j, cellSize);
			}
		}

	}

	public void randomizeCells() {
		// 4. Iterate through each cell and randomly set each
		// cell's isAlive memeber to true of false
		for (int i = 0; i < ac.length; i++) {
			for (int j = 0; j < ac[i].length; j++) {
				if (rand.nextInt(3) == 0) {
					ac[i][j].isAlive = true;
				} else {
					ac[i][j].isAlive = false;
				}
			}
		}
		repaint();
	}

	public void clearCells() {
		// 5. Iterate through the cells and set them all to dead.
		for (int i = 0; i < ac.length; i++) {
			for (int j = 0; j < ac[i].length; j++) {
				ac[i][j].isAlive = false;
			}
		}
		repaint();
	}

	public void startAnimation() {
		timer.start();
	}

	public void stopAnimation() {
		timer.stop();
	}

	public void setAnimationDelay(int sp) {
		timer.setDelay(sp);
	}

	@Override
	public void paintComponent(Graphics g) {
		// 6. Iterate through the cells and draw them all
		for (int i = 0; i < ac.length; i++) {
			for (int j = 0; j < ac[i].length; j++) {
				ac[i][j].draw(g);
			}
		}

		// draws grid
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
	}

	// advances world one step
	public void step() {
		// 7. iterate through cells and fill in the livingNeighbors array
		// . using the getLivingNeighbors method.
		int[][] livingNeighbors = new int[cellsPerRow][cellsPerRow];
		System.out.println("a");
		for (int i = 0; i < livingNeighbors.length; i++) {
			for (int j = 0; j < livingNeighbors[i].length; j++) {
				livingNeighbors[i][j] = getLivingNeighbors(i, j);
			}
		}
		System.out.println("b");
		// 8. check if each cell should live or die
		for (int i = 0; i < ac.length; i++) {
			for (int j = 0; j < ac[i].length; j++) {
				ac[i][j].liveOrDie(livingNeighbors[i][j]);
			}
		}
		System.out.println("c");
		repaint();
	}
	
	// 9. Complete the method.
	// It returns an int of 8 or less based on how many
	// living neighbors there are of the
	// cell identified by x and y
	public int getLivingNeighbors(int x, int y) {
		int n = 0;
		if (x > 0) {
			if (y > 0) {
				if (ac[x - 1][y - 1].isAlive) {
					n++;
				}
			}
		}
		if (y > 0) {
			if (ac[x][y - 1].isAlive) {
				n++;
			}
		}
		if (x < cellsPerRow-1) {
			if (y<cellsPerRow-1) {
				if (ac[x + 1][y + 1].isAlive) {
					n++;
				}
			}
		}
		if (x < cellsPerRow-1) {
			if (ac[x + 1][y].isAlive) {
				n++;
			}
		}
		if (x < -1) {
			if (y>0) {
				if (ac[x + 1][y - 1].isAlive) {
					n++;
				}
			}
		}
		if (y < cellsPerRow-1) {
			if (ac[x][y + 1].isAlive) {
				n++;
			}
		}
		if (x > 0) {
			if (y < cellSize-1) {
				if (ac[x - 1][y + 1].isAlive) {
					n++;
				}
			}
		}
		if (x > 0) {
			if (ac[x - 1][y].isAlive) {
				n++;
			}
		}
		return n;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// 10. Use e.getX() and e.getY() to determine
		// which cell is clicked. Then toggle
		// the isAlive variable for that cell.
		for (int i = 0; i < ac.length; i++) {
			for (int j = 0; j < ac[i].length; j++) {
				if (e.getX() >= ac[i][j].getX()*cellSize && ac[i][j].getX()*cellSize + cellSize >= e.getX()) {
					if (e.getY() >= ac[i][j].getY()*cellSize && ac[i][j].getY()*cellSize + cellSize >= e.getY()) {
						ac[i][j].isAlive = !ac[i][j].isAlive;
					}
				}
			}
		}
		repaint();
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		step();
	}
}
