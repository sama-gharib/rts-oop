package fr.uvsq.amis;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.geom.Point2D;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.Timer;

class GamePanel extends JPanel {
	HexagonBasis basis = new HexagonBasis(50.f);
	
	public GamePanel() {
		Timer time = new Timer(1000/60, e -> this.repaint());
		time.start();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Vector2D targeted = this.basis.getIndices(
			new Vector2D(MouseInfo.getPointerInfo().getLocation())
				.sub(new Vector2D(this.getLocationOnScreen()))
		).asIndices();
		
		g.setColor(Color.BLACK);
		for (int i = 3; i < 6; i++) {
			for (int j = 3; j < 6; j++) {
				Vector2D coords = this.basis.getWorldPosition(new Vector2D(i, j));
				if ((int) targeted.getX() == i && (int) targeted.getY() == j) {
					g.setColor(Color.RED);
				} else {
					g.setColor(Color.BLACK);
				}
				this.drawHexagon(g, (int) coords.getX(), (int) coords.getY());
			}
		}
	}

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
