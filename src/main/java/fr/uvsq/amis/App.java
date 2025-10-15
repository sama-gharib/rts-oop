package fr.uvsq.amis;

import javax.swing.JFrame;

/**
 * Application entry-point.
 */
public class App {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(500, 500);
        window.add(new GamePanel());
        window.setVisible(true);
    }
}
