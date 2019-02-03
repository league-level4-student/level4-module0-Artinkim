package _02_Pixel_Art;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GridPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private int windowWidth;
	private int windowHeight;
	private int pixelWidth;
	private int pixelHeight;
	private int rows;
	private int cols;

	// 1. Create a 2D array of pixels. Do not initialize it yet.
	Pixel[][] pa;
	private Color color;

	public GridPanel(int w, int h, int r, int c) {
		this.windowWidth = w;
		this.windowHeight = h;
		this.rows = r;
		this.cols = c;

		this.pixelWidth = windowWidth / cols;
		this.pixelHeight = windowHeight / rows;

		color = Color.BLACK;

		setPreferredSize(new Dimension(windowWidth, windowHeight));

		// 2. Initialize the pixel array using the rows and cols variables.
		pa = new Pixel[rows][cols];

		// 3. Iterate through the array and initialize each element to a new pixel.
		for (int i = 0; i < pa.length; i++) {
			for (int j = 0; j < pa[i].length; j++) {
				pa[i][j] = new Pixel(i*pixelWidth, j*pixelHeight);
			}
		}
	}

	public void setColor(Color c) {
		color = c;
	}

	public void clickPixel(int mouseX, int mouseY) {
		// 5. Use the mouseX and mouseY variables to change the color
		// of the pixel that was clicked. *HINT* Use the pixel's dimensions.
		for (int i = 0; i < pa.length; i++) {
			for (int j = 0; j < pa[i].length; j++) {
				if (mouseX >= pa[i][j].x && pa[i][j].x + pixelWidth >= mouseX) {
					if (mouseY >= pa[i][j].y && pa[i][j].y + pixelHeight >= mouseY) {
						pa[i][j].color = color;
					}

				}
			}
		}

	}

	@Override
	public void paintComponent(Graphics g) {
		// 4. Iterate through the array.
		// For every pixel in the list, fill in a rectangle using the pixel's color.
		// Then, use drawRect to add a grid pattern to your display.
		for (int i = 0; i < pa.length; i++) {
			for (int j = 0; j < pa[i].length; j++) {
				g.setColor(pa[i][j].color);
				g.fillRect(i * pixelWidth, j * pixelHeight, pixelWidth, pixelHeight);
				g.setColor(new Color(0, 0, 0));
				g.drawRect(i * pixelWidth, j * pixelHeight, pixelWidth, pixelHeight);
			}

		}
	}
}
