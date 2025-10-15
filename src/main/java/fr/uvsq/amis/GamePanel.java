package fr.uvsq.amis;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Displays the world in real time.
 */
class GamePanel extends JPanel {
	/**
	 * This attribute should be wrapped in a `GameModel` class.
	 */
	HexagonBasis basis = new HexagonBasis(50.f);

	/**
	 * Launches the display loop.
	 */
	public GamePanel() {
		Timer time = new Timer(1000/60, e -> this.repaint());
		time.start();
	}

	/**
	 * Drawing the world.
	 */
	@Override
	public void paintComponent(Graphics g) {
		// targeted: The indices of the tile under the mouse.
		Vector2D targeted = this.basis.getIndices(
			new Vector2D(MouseInfo.getPointerInfo().getLocation())
				.sub(new Vector2D(this.getLocationOnScreen()))
		).asIndices();

		// Drawing the grid of tiles.
		// Maybe this should be extracted in a `TileMap::draw()` method.
		for (int line = 3; line < 6; line++) {
			for (int column = 3; column < 6; column++) {
				// coords: Center of the hexagon at (line; column) in the grid.
				Vector2D coords = this.basis.getWorldPosition(new Vector2D(line, column));
				
				// Setting the color to red if current hexagon is targeted.
				if ((int) targeted.getX() == line && (int) targeted.getY() == column) {
					g.setColor(Color.RED);
				} else {
					g.setColor(Color.BLACK);
				}
				this.drawHexagon(g, (int) coords.getX(), (int) coords.getY());
			}
		}
	}

	/**
	 * This should be extracted in a `Tile::draw()` class.
	 */
	private void drawHexagon(Graphics g, int x, int y) {
		int[] a = new int[7];
		int[] b = new int[7];
		for (int i = 0; i < 7; i++) {
			float angle = (float) Math.PI * 2.f/6.f * (float) i - (float) (Math.PI / 2.f);
			a[i] = (int) (Math.cos(angle) * this.basis.getRadius()) + x;
			b[i] = (int) (Math.sin(angle) * this.basis.getRadius()) + y;
		}
		
		g.drawPolygon(a, b, 7);
	}
}
